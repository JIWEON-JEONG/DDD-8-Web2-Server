package ddd.caffeine.ratrip.module.place.presentation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.common.model.Region;
import ddd.caffeine.ratrip.common.annotation.UUIDFormat;
import ddd.caffeine.ratrip.module.place.application.PlaceService;
import ddd.caffeine.ratrip.module.place.presentation.dto.bookmark.BookmarkPlaceByCoordinateRequestDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.bookmark.BookmarkPlaceResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.bookmark.BookmarkPlacesByCoordinateResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.bookmark.BookmarkPlacesByRegionResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.bookmark.BookmarkResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.request.CategoryPlaceByCoordinateRequestDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.request.CategoryPlaceByRegionRequestDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.request.PlaceCoordinateRequestDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.request.PlaceSaveByThirdPartyRequestDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.request.PlaceSearchRequestDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.response.CategoryPlacesByCoordinateResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.response.CategoryPlacesByRegionResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.response.PlaceDetailResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.response.PlaceInRegionResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.response.PlaceSaveThirdPartyResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.response.PlaceSearchResponseDto;
import ddd.caffeine.ratrip.module.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

/**
 * 장소 API
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "v1/places")
public class PlaceController {
	private final PlaceService placeService;

	@Operation(summary = "[인증] 장소 키워드 검색 API")
	@GetMapping("search")
	public ResponseEntity<PlaceSearchResponseDto> callPlaceSearchApi(
		@Valid @ModelAttribute PlaceSearchRequestDto request) {

		PlaceSearchResponseDto response = placeService.searchPlaces(request.mapByThirdPartySearchOption());
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "[인증] 카카오 정보를 통한 장소 저장 및 업데이트 API")
	@PostMapping
	public ResponseEntity<PlaceSaveThirdPartyResponseDto> callSavePlaceByThirdPartyData(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@Valid @RequestBody PlaceSaveByThirdPartyRequestDto request) {

		PlaceSaveThirdPartyResponseDto response = placeService.savePlaceByThirdPartyData(
			user, request.mapByThirdPartyDetailSearchOption());
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "[인증] 장소 기본키(UUID)로 장소 상세 읽기 API")
	@GetMapping("/{id}")
	public ResponseEntity<PlaceDetailResponseDto> callPlaceDetailsApiByUUID(
		@PathVariable @UUIDFormat String id) {

		PlaceDetailResponseDto response = placeService.readPlaceDetailsByUUID(id);
		return ResponseEntity.ok(response);
	}

	/**
	 * default page = 0
	 * Todo : default size 정하기.
	 */
	@Operation(summary = "[인증] 지역기반 장소 불러오기 (default 옵션 : 인기순정렬, 데이터 5개씩, 내림차순)")
	@GetMapping("/regions")
	public ResponseEntity<PlaceInRegionResponseDto> callPlacesInRegionsApi(
		@RequestParam(name = "region") List<Region> regions,
		@PageableDefault(
			size = 5, sort = "popular", direction = Sort.Direction.DESC) Pageable pageable) {
		PlaceInRegionResponseDto response = placeService.readPlacesInRegions(regions, pageable);
		return ResponseEntity.ok(response);
	}

	/**
	 * default page = 0
	 * Todo : default size 정하기.
	 */
	@Operation(summary = "[인증] 좌표데이터를 통해 위치 기반 장소 불러오기 (default 옵션 : 인기순정렬, 데이터 5개씩, 내림차순)")
	@GetMapping("/coordinates")
	public ResponseEntity<PlaceInRegionResponseDto> callPlacesInCoordinateApi(
		@Valid @ModelAttribute PlaceCoordinateRequestDto request,
		@PageableDefault(
			size = 5, sort = "popular", direction = Sort.Direction.DESC) Pageable pageable) {
		PlaceInRegionResponseDto response = placeService.readPlacesInCoordinate(request.toServiceDto(),
			pageable);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "[인증] 카테고리별 북마크 리스트 페이지네이션 조회")
	@GetMapping("/bookmarks")
	public ResponseEntity<BookmarkPlaceResponseDto> callReadBookmarksApi(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@RequestParam(name = "category", defaultValue = "모든 카테고리", required = false) List<String> categories,
		@PageableDefault(size = 7, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
		BookmarkPlaceResponseDto response = placeService.readBookmarks(user, categories, pageable);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "[인증] 특정 장소에 대한 북마크 조회")
	@GetMapping("/{place_id}/bookmarks")
	public ResponseEntity<BookmarkResponseDto> callReadBookmarkOfPlaceApi(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@PathVariable(name = "place_id") @UUIDFormat String placeUUID) {
		BookmarkResponseDto response = placeService.readBookmark(user, placeUUID);

		return ResponseEntity.ok(response);
	}

	@Operation(summary = "[인증] 북마크 생성")
	@PostMapping("/{place_id}/bookmarks")
	public ResponseEntity<BookmarkResponseDto> callCreateBookmarkApi(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@PathVariable(name = "place_id") @UUIDFormat String placeUUID) {
		BookmarkResponseDto response = placeService.createBookmark(user, placeUUID);

		return ResponseEntity.ok(response);
	}

	@Operation(summary = "[인증] 북마크 상태 변경")
	@PatchMapping("/{place_id}/bookmarks")
	public ResponseEntity<BookmarkResponseDto> callChangeBookmarkStateApi(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@PathVariable(name = "place_id") @UUIDFormat String placeUUID) {
		BookmarkResponseDto response = placeService.changeBookmarkState(user, placeUUID);

		return ResponseEntity.ok(response);
	}

	@Operation(summary = "[인증] 유저가 선택한 지역 기반 북마크 추천 페이지네이션 조회")
	@GetMapping("/bookmarks/regions")
	public ResponseEntity<BookmarkPlacesByRegionResponseDto> getBookmarkPlacesByRegion(
		@Parameter(hidden = true) @AuthenticationPrincipal User user, @RequestParam Region region,
		@PageableDefault(size = 20) Pageable pageable) {

		return ResponseEntity.ok(placeService.getBookmarkPlacesByRegion(user, region, pageable));
	}

	@Operation(summary = "[인증] 유저가 현재 위치 기반 북마크 추천 페이지네이션 조회")
	@GetMapping("/bookmarks/coordinates")
	public ResponseEntity<BookmarkPlacesByCoordinateResponseDto> getBookmarkPlacesByCoordinate(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@Valid @ModelAttribute BookmarkPlaceByCoordinateRequestDto request,
		@PageableDefault(size = 20) Pageable pageable) {

		return ResponseEntity.ok(placeService.getBookmarkPlacesByCoordinate(user, request.toServiceDto(), pageable));
	}

	@Operation(summary = "[인증] 유저가 선택한 지역 기반 카테고리 추천 페이지네이션 조회")
	@GetMapping("/categories/regions")
	public ResponseEntity<CategoryPlacesByRegionResponseDto> getCategoryPlacesByRegion(
		@Valid @ModelAttribute CategoryPlaceByRegionRequestDto request,
		@PageableDefault(size = 20) Pageable pageable) {

		return ResponseEntity.ok(placeService.getCategoryPlacesByRegion(request.toServiceDto(), pageable));
	}

	@Operation(summary = "[인증] 유저가 현재 위치 기반 카테고리 추천 페이지네이션 조회")
	@GetMapping("/categories/coordinates")
	public ResponseEntity<CategoryPlacesByCoordinateResponseDto> getCategoryPlacesByCoordinate(
		@Valid @ModelAttribute CategoryPlaceByCoordinateRequestDto request,
		@PageableDefault(size = 20) Pageable pageable) {

		return ResponseEntity.ok(placeService.getCategoryPlacesByCoordinate(request.toServiceDto(), pageable));
	}
}
