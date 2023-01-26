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
import ddd.caffeine.ratrip.module.place.feign.naver.model.FeignBlogModel;
import ddd.caffeine.ratrip.module.place.feign.naver.model.FeignImageModel;
import ddd.caffeine.ratrip.module.place.presentation.dto.region.PlaceInRegionResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.bookmark.BookmarkPlaceResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.bookmark.BookmarkResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.detail.PlaceDetailResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.save.PlaceSaveThirdPartyResponseDto;
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
	public PlaceDetailResponseDto readPlaceDetailsByUUID(String uuid, User user) {
		Place place = readPlaceByUUID(UUID.fromString(uuid));
		BookmarkResponseDto bookMarkModel = bookmarkService.readBookmarkModel(user, place);
		return new PlaceDetailResponseDto(place, bookMarkModel);
	}

	@Transactional
	public PlaceSaveThirdPartyResponseDto savePlaceByThirdPartyData(ThirdPartyDetailSearchOption searchOption,
		User user) {
		BookmarkResponseDto bookmarkModel;
		Place place = placeRepository.findByThirdPartyID(searchOption.readThirdPartyId());

		if (place == null) {
			Place entity = readPlaceEntity(searchOption.readPlaceNameAndAddress());
			placeRepository.save(entity);
			bookmarkModel = bookmarkService.readBookmarkModel(user, entity);
			return new PlaceSaveThirdPartyResponseDto(entity, bookmarkModel);
		}

		handlePlaceUpdate(place, searchOption.readPlaceNameAndAddress());
		bookmarkModel = bookmarkService.readBookmarkModel(user, place);
		return new PlaceSaveThirdPartyResponseDto(place, bookmarkModel);
	}

	@Transactional
	public BookmarkResponseDto changeBookmarkState(UUID placeUUID, UUID bookmarkUUID) {
		Optional<Place> place = placeRepository.findById(placeUUID);
		placeValidator.validateExistPlace(place);
		return bookmarkService.changeBookmarkState(bookmarkUUID);
	}

	@Transactional(readOnly = true)
	public BookmarkPlaceResponseDto readBookmarks(User user, List<String> categories, Pageable pageable) {
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
			FeignPlaceModel feignPlaceModel = placeFeignService.readPlacesByAddressAndPlaceName(
				placeNameAndAddressMap.get("address"), placeNameAndAddressMap.get("name"));
			place.update(feignPlaceModel.readOne());
			injectImageLinkInPlace(place, place.getName());
			injectBlogsInPlace(place, place.getName());
		}
	}

	/**
	 * 장소이름과 주소를 가지고 그에 맞는 Place Entity 생성해주는 메서드.
	 */
	private Place readPlaceEntity(Map<String, String> placeNameAndAddressMap) {
		FeignPlaceModel feignPlaceModel = placeFeignService.readPlacesByAddressAndPlaceName(
			placeNameAndAddressMap.get("address"), placeNameAndAddressMap.get("name"));
		Place place = feignPlaceModel.mapByPlaceEntity();

		injectImageLinkInPlace(place, place.getName());
		injectBlogsInPlace(place, place.getName());

		return place;
	}

	private void injectImageLinkInPlace(Place place, String keyword) {
		final int DATA_INDEX = 0;

		FeignImageModel imageModel = placeFeignService.readImageModel(keyword);
		place.injectImageLink(imageModel.readImageLinkByIndex(DATA_INDEX));
	}

	private void injectBlogsInPlace(Place place, String keyword) {
		FeignBlogModel blogModel = placeFeignService.readBlogModel(keyword);
		place.injectBlogs(blogModel.readBlogs());
	}

}
