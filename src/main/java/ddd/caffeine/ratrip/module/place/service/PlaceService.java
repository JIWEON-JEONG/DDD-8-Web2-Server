package ddd.caffeine.ratrip.module.place.service;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ddd.caffeine.ratrip.common.exception.domain.PlaceException;
import ddd.caffeine.ratrip.module.feign.domain.place.PlaceFeignService;
import ddd.caffeine.ratrip.module.feign.domain.place.kakao.model.PlaceKakaoModel;
import ddd.caffeine.ratrip.module.feign.domain.place.naver.model.ImageNaverModel;
import ddd.caffeine.ratrip.module.place.model.Place;
import ddd.caffeine.ratrip.module.place.model.Region;
import ddd.caffeine.ratrip.module.place.presentation.dto.PlaceDetailsResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.PlaceSearchResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.PopularPlaceResponse;
import ddd.caffeine.ratrip.module.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceService {

	private final PlaceFeignService placeFeignService;
	private final PlaceValidator placeValidator;
	private final PlaceRepository placeRepository;

	public PopularPlaceResponse readPopularPlaces(List<String> regions) {
		final int POPULAR_PLACE_COUNT = 10;
		List<Place> popularPlaces = placeRepository.findPopularPlacesInRegions(Region.createRegions(regions),
			POPULAR_PLACE_COUNT);
		return new PopularPlaceResponse(popularPlaces);
	}

	public PlaceSearchResponseDto searchPlaces(String keyword, String latitude, String longitude, int page) {
		validateSearchRequestParameters(latitude, longitude, page);
		PlaceKakaoModel placeKakaoModel = placeFeignService.readPlaces(keyword, latitude, longitude, page);

		return placeKakaoModel.mapByPlaceSearchResponseDto();
	}

	public PlaceDetailsResponseDto readPlaceDetailsByUUID(String uuid) {
		Optional<Place> optionalPlace = placeRepository.findById(UUID.fromString(uuid));
		optionalPlace.orElseThrow(() -> new PlaceException(NOT_FOUND_PLACE_EXCEPTION));

		return new PlaceDetailsResponseDto(optionalPlace.get());
	}

	public PlaceDetailsResponseDto readPlaceDetailsByThirdPartyId(String thirdPartyId, String address,
		String placeName) {
		Optional<Place> optionalPlace = placeRepository.findByKakaoId(thirdPartyId);

		// @TODO 이부분 조금 더 깔끔하게 할 수 있을거같긴한데.. 잘 떠오르질 않음 -> 추후 좋은 방법 있을 경우 리팩토링.
		if (optionalPlace.isEmpty()) {
			Place place = readPlaceEntity(address, placeName);
			placeRepository.save(place);
			return new PlaceDetailsResponseDto(place);
		}

		Place place = optionalPlace.get();
		handlePlaceUpdate(place, address, placeName);
		return new PlaceDetailsResponseDto(place);
	}

	/**
	 * 장소 데이터 업데이트 처리 메서드.
	 */
	private void handlePlaceUpdate(Place place, String address, String placeName) {
		if (place.checkNeedsUpdate(address, placeName)) {
			PlaceKakaoModel placeKakaoModel = placeFeignService.readOnePlace(address, placeName);
			place.update(placeKakaoModel.readPlaceDataIndexZero());
		}
	}

	/**
	 * 장소이름과 주소를 가지고 그에 맞는 Place Entity 생성해주는 메서드.
	 */
	private Place readPlaceEntity(String address, String placeName) {
		final int DATA_INDEX = 0;

		PlaceKakaoModel placeKakaoModel = placeFeignService.readOnePlace(address, placeName);
		Place place = placeKakaoModel.mapByPlaceEntity();

		ImageNaverModel imageModel = placeFeignService.readImageModel(placeName);
		place.injectImageLink(imageModel.readImageLinkByIndex(DATA_INDEX));

		return place;
	}

	private void validateSearchRequestParameters(String latitude, String longitude, int page) {
		placeValidator.validateRangeLatitude(latitude);
		placeValidator.validateRangeLongitude(longitude);
		placeValidator.validatePageSize(page);
	}
}
