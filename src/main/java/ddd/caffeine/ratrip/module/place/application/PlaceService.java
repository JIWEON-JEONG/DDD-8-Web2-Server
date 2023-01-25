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
import ddd.caffeine.ratrip.module.place.feign.kakao.model.PlaceKakaoModel;
import ddd.caffeine.ratrip.module.place.feign.naver.model.ImageNaverModel;
import ddd.caffeine.ratrip.module.place.presentation.dto.PlaceInRegionResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.bookmark.BookmarkAddResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.bookmark.BookmarksResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.detail.PlaceDetailsResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.detail.PlaceSaveThirdPartyResponseDto;
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
		PlaceKakaoModel placeKakaoModel = placeFeignService.readPlacesByKeywordAndCoordinate(
			searchOption);

		return placeKakaoModel.mapByPlaceSearchResponseDto();
	}

	@Transactional(readOnly = true)
	public PlaceDetailsResponseDto readPlaceDetailsByUUID(String uuid, User user) {
		Place place = readPlaceByUUID(UUID.fromString(uuid));
		boolean isBookmarked = bookmarkService.isBookmarked(user, place);
		return new PlaceDetailsResponseDto(place, isBookmarked);
	}

	@Transactional
	public PlaceSaveThirdPartyResponseDto savePlaceByThirdPartyData(ThirdPartyDetailSearchOption searchOption,
		User user) {
		Optional<Place> optionalPlace = placeRepository.findByKakaoId(searchOption.readThirdPartyId());

		if (optionalPlace.isEmpty()) {
			Place place = readPlaceEntity(searchOption.readPlaceNameAndAddress());
			placeRepository.save(place);
			return new PlaceSaveThirdPartyResponseDto(place, Boolean.FALSE);
		}

		Place place = optionalPlace.get();
		handlePlaceUpdate(place, searchOption.readPlaceNameAndAddress());
		boolean isBookmarked = bookmarkService.isBookmarked(user, place);

		return new PlaceSaveThirdPartyResponseDto(place, isBookmarked);
	}

	@Transactional
	public BookmarkAddResponseDto addBookMark(UUID placeId, User user) {
		Optional<Place> place = placeRepository.findById(placeId);
		placeValidator.validateExistPlace(place);
		UUID bookmarkId = bookmarkService.addBookmark(user, place.get());

		return new BookmarkAddResponseDto(bookmarkId);
	}

	@Transactional
	public void deleteBookMark(UUID placeId, User user) {
		Optional<Place> optionalPlace = placeRepository.findById(placeId);
		Place place = placeValidator.validateExistPlace(optionalPlace);
		bookmarkService.deleteBookmark(user, place);
	}

	@Transactional(readOnly = true)
	public BookmarksResponseDto readBookmarks(User user, List<String> categories, Pageable pageable) {
		return bookmarkService.getBookmarks(user, categories, pageable);
	}

	public Place readPlaceByUUID(UUID placeUUID) {
		Optional<Place> place = placeRepository.findById(placeUUID);
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
