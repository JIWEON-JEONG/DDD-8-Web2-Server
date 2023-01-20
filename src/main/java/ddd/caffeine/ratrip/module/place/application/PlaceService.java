package ddd.caffeine.ratrip.module.place.application;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.common.model.Region;
import ddd.caffeine.ratrip.module.place.domain.Place;
import ddd.caffeine.ratrip.module.place.domain.ThirdPartyDetailSearchOption;
import ddd.caffeine.ratrip.module.place.domain.ThirdPartySearchOption;
import ddd.caffeine.ratrip.module.place.domain.repository.PlaceRepository;
import ddd.caffeine.ratrip.module.place.feign.PlaceFeignService;
import ddd.caffeine.ratrip.module.place.feign.kakao.model.FeignPlaceModel;
import ddd.caffeine.ratrip.module.place.feign.naver.model.FeignImageModel;
import ddd.caffeine.ratrip.module.place.presentation.dto.PlaceInRegionResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.bookmark.BookmarksResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.detail.PlaceDetailsResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.search.PlaceSearchResponseDto;
import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class PlaceService {

	private final PlaceFeignService placeFeignService;
	private final PlaceValidator placeValidator;
	private final BookmarkService bookmarkService;
	private final PlaceRepository placeRepository;

	@Transactional(readOnly = true)
	public PlaceInRegionResponseDto readPlacesInRegions(List<String> regions, Pageable page) {
		Slice<Place> places = placeRepository.findPlacesInRegions(Region.createRegions(regions), page);
		return new PlaceInRegionResponseDto(places.getContent(), places.hasNext());
	}

	@Transactional(readOnly = true)
	public PlaceSearchResponseDto searchPlaces(ThirdPartySearchOption searchOption) {
		FeignPlaceModel feignPlaceModel = placeFeignService.readPlacesByKeywordAndCoordinate(
			searchOption);

		return feignPlaceModel.mapByPlaceSearchResponseDto();
	}

	@Transactional(readOnly = true)
	public PlaceDetailsResponseDto readPlaceDetailsByUUID(String uuid, User user) {
		Optional<Place> place = placeRepository.findById(UUID.fromString(uuid));
		placeValidator.validateExistPlace(place);

		boolean isBookmarked = bookmarkService.isBookmarked(user, place.get());
		return new PlaceDetailsResponseDto(place.get(), isBookmarked);
	}

	@Transactional
	public PlaceDetailsResponseDto readPlaceDetailsByThirdPartyId(ThirdPartyDetailSearchOption searchOption,
		User user) {

		Optional<Place> optionalPlace = placeRepository.findByKakaoId(searchOption.readThirdPartyId());

		if (optionalPlace.isEmpty()) {
			Place place = readPlaceEntity(searchOption.readPlaceNameAndAddress());
			placeRepository.save(place);
			return new PlaceDetailsResponseDto(place, Boolean.FALSE);
		}

		Place place = optionalPlace.get();
		handlePlaceUpdate(place, searchOption.readPlaceNameAndAddress());
		boolean isBookmarked = bookmarkService.isBookmarked(user, place);

		return new PlaceDetailsResponseDto(place, isBookmarked);
	}

	@Transactional
	public UUID addBookMark(UUID placeId, User user) {
		Optional<Place> place = placeRepository.findById(placeId);
		placeValidator.validateExistPlace(place);
		UUID bookmarkID = bookmarkService.addBookmark(user, place.get());

		return bookmarkID;
	}

	@Transactional
	public UUID deleteBookMark(UUID placeId, User user) {
		Optional<Place> place = placeRepository.findById(placeId);
		placeValidator.validateExistPlace(place);
		bookmarkService.deleteBookmark(user, place.get());

		return UUID.randomUUID();
	}

	@Transactional(readOnly = true)
	public BookmarksResponseDto readBookmarks(User user, List<String> categories, Pageable pageable) {
		return bookmarkService.getBookmarks(user, categories, pageable);
	}

	/**
	 * 장소 데이터 업데이트 처리 메서드.
	 */
	private void handlePlaceUpdate(Place place, Map<String, String> placeNameAndAddressMap) {
		if (place.checkNeedsUpdate(placeNameAndAddressMap.get("address"), placeNameAndAddressMap.get("name"))) {
			FeignPlaceModel feignPlaceModel = placeFeignService.readPlacesByAddressAndPlaceName(
				placeNameAndAddressMap.get("address"), placeNameAndAddressMap.get("name"));
			place.update(feignPlaceModel.readOne());
		}
	}

	/**
	 * 장소이름과 주소를 가지고 그에 맞는 Place Entity 생성해주는 메서드.
	 */
	private Place readPlaceEntity(Map<String, String> placeNameAndAddressMap) {
		final int DATA_INDEX = 0;

		FeignPlaceModel feignPlaceModel = placeFeignService.readPlacesByAddressAndPlaceName(
			placeNameAndAddressMap.get("address"), placeNameAndAddressMap.get("name"));
		Place place = feignPlaceModel.mapByPlaceEntity();

		FeignImageModel imageModel = placeFeignService.readImageModel(placeNameAndAddressMap.get("name"));
		place.injectImageLink(imageModel.readImageLinkByIndex(DATA_INDEX));

		return place;
	}

}
