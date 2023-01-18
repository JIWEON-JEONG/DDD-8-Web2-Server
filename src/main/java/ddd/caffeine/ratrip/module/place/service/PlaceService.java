package ddd.caffeine.ratrip.module.place.service;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.common.exception.domain.PlaceException;
import ddd.caffeine.ratrip.common.model.Region;
import ddd.caffeine.ratrip.module.place.feign.PlaceFeignService;
import ddd.caffeine.ratrip.module.place.feign.kakao.model.PlaceKakaoModel;
import ddd.caffeine.ratrip.module.place.feign.naver.model.ImageNaverModel;
import ddd.caffeine.ratrip.module.place.model.Place;
import ddd.caffeine.ratrip.module.place.model.ThirdPartyDetailSearchOption;
import ddd.caffeine.ratrip.module.place.model.ThirdPartySearchOption;
import ddd.caffeine.ratrip.module.place.presentation.dto.PlaceInRegionResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.detail.PlaceDetailsResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.search.PlaceSearchResponseDto;
import ddd.caffeine.ratrip.module.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class PlaceService {

	private final PlaceFeignService placeFeignService;
	private final PlaceRepository placeRepository;
	private final PlaceValidator placeValidator;

	@Transactional(readOnly = true)
	public PlaceInRegionResponseDto readPlacesInRegionsApi(List<String> regions, Pageable page) {
		Slice<Place> places = placeRepository.findPlacesInRegions(Region.createRegions(regions), page);
		return new PlaceInRegionResponseDto(places.getContent(), places.hasNext());
	}

	@Transactional(readOnly = true)
	public PlaceSearchResponseDto searchPlaces(ThirdPartySearchOption searchOption) {
		PlaceKakaoModel placeKakaoModel = placeFeignService.readPlacesByKeywordAndCoordinate(
			searchOption);

		return placeKakaoModel.mapByPlaceSearchResponseDto();
	}

	@Transactional(readOnly = true)
	public PlaceDetailsResponseDto readPlaceDetailsByUUID(String uuid) {
		Optional<Place> place = placeRepository.findById(UUID.fromString(uuid));
		place.orElseThrow(() -> new PlaceException(NOT_FOUND_PLACE_EXCEPTION));

		return new PlaceDetailsResponseDto(place.get());
	}

	@Transactional
	public PlaceDetailsResponseDto readPlaceDetailsByThirdPartyId(ThirdPartyDetailSearchOption searchOption) {
		Optional<Place> optionalPlace = placeRepository.findByKakaoId(searchOption.readThirdPartyId());

		// @TODO 이부분 조금 더 깔끔하게 할 수 있을거같긴한데.. 잘 떠오르질 않음 -> 추후 좋은 방법 있을 경우 리팩토링.
		if (optionalPlace.isEmpty()) {
			Place place = readPlaceEntity(searchOption.readPlaceNameAndAddress());
			placeRepository.save(place);
			return new PlaceDetailsResponseDto(place);
		}

		Place place = optionalPlace.get();
		handlePlaceUpdate(place, searchOption.readPlaceNameAndAddress());
		return new PlaceDetailsResponseDto(place);
	}

	@Transactional(readOnly = true)
	public Place findPlaceById(UUID id) {
		Optional<Place> place = placeRepository.findById(id);
		return placeValidator.validateExistPlace(place);
	}

	/**
	 * 장소 데이터 업데이트 처리 메서드.
	 */
	private void handlePlaceUpdate(Place place, Map<String, String> placeNameAndAddressMap) {
		if (place.checkNeedsUpdate(placeNameAndAddressMap.get("address"), placeNameAndAddressMap.get("name"))) {
			PlaceKakaoModel placeKakaoModel = placeFeignService.readPlacesByAddressAndPlaceName(
				placeNameAndAddressMap.get("address"), placeNameAndAddressMap.get("name"));
			place.update(placeKakaoModel.readOne());
		}
	}

	/**
	 * 장소이름과 주소를 가지고 그에 맞는 Place Entity 생성해주는 메서드.
	 */
	private Place readPlaceEntity(Map<String, String> placeNameAndAddressMap) {
		final int DATA_INDEX = 0;

		PlaceKakaoModel placeKakaoModel = placeFeignService.readPlacesByAddressAndPlaceName(
			placeNameAndAddressMap.get("address"), placeNameAndAddressMap.get("name"));
		Place place = placeKakaoModel.mapByPlaceEntity();

		ImageNaverModel imageModel = placeFeignService.readImageModel(placeNameAndAddressMap.get("name"));
		place.injectImageLink(imageModel.readImageLinkByIndex(DATA_INDEX));

		return place;
	}
}
