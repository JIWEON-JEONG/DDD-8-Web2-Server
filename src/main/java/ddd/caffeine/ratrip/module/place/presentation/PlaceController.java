package ddd.caffeine.ratrip.module.place.presentation;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.common.validator.annotation.UUIDFormat;
import ddd.caffeine.ratrip.module.place.application.PlaceService;
import ddd.caffeine.ratrip.module.place.presentation.dto.PlaceInRegionResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.bookmark.BookmarksResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.detail.PlaceDetailsByThirdPartyRequestDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.detail.PlaceDetailsResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.search.PlaceSearchRequestDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.search.PlaceSearchResponseDto;
import ddd.caffeine.ratrip.module.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

	@GetMapping("search")
	public ResponseEntity<PlaceSearchResponseDto> callPlaceSearchApi(
		@Valid @ModelAttribute PlaceSearchRequestDto request) {

		PlaceSearchResponseDto response = placeService.searchPlaces(request.mapByThirdPartySearchOption());
		return ResponseEntity.ok(response);
	}

	@GetMapping("third-party")
	public ResponseEntity<PlaceDetailsResponseDto> callPlaceDetailsApiByThirdPartyId(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@Valid @ModelAttribute PlaceDetailsByThirdPartyRequestDto request) {

		PlaceDetailsResponseDto response = placeService.readPlaceDetailsByThirdPartyId(
			request.mapByThirdPartyDetailSearchOption(), user);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PlaceDetailsResponseDto> callPlaceDetailsApiByUUID(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@PathVariable @UUIDFormat String id) {

		PlaceDetailsResponseDto response = placeService.readPlaceDetailsByUUID(id, user);
		return ResponseEntity.ok(response);
	}

	/**
	 * default page = 0
	 * Todo : default size 정하기.
	 */
	@GetMapping(value = "regions")
	public ResponseEntity<PlaceInRegionResponseDto> callPlacesInRegionsApi(
		@RequestParam(name = "region", required = false, defaultValue = "전국") List<String> regions,
		@PageableDefault(
			size = 5, sort = "popular", direction = Sort.Direction.DESC) Pageable pageable) {
		PlaceInRegionResponseDto response = placeService.readPlacesInRegions(regions, pageable);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "[인증] 북마크 추가")
	@ApiResponse(description = "북마크 추가 성공 시, 북마크 ID 반환")
	@PostMapping("/{id}/bookmarks")
	public ResponseEntity<UUID> callAddBookmarkApi(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@PathVariable @UUIDFormat String id) {
		UUID response = placeService.addBookMark(UUID.fromString(id), user);

		return ResponseEntity.ok(response);
	}

	@Operation(summary = "[인증] 북마크 삭제")
	@DeleteMapping("/{id}/bookmarks")
	public ResponseEntity<String> callDeleteBookmarkApi(
		@PathVariable @UUIDFormat String id,
		@Parameter(hidden = true) @AuthenticationPrincipal User user) {
		placeService.deleteBookMark(UUID.fromString(id), user);
		return ResponseEntity.ok("SUCCESS TO DELETE");
	}

	//TODO - 인자를 Enum 타입으로 받는 법 알아보기
	@Operation(summary = "[인증] 북마크 리스트 조회")
	@GetMapping("/bookmarks")
	public ResponseEntity<BookmarksResponseDto> getBookmarks(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@RequestParam(name = "category", defaultValue = "모든 카테고리", required = false) List<String> categories,
		@PageableDefault(size = 7, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
		BookmarksResponseDto response = placeService.readBookmarks(user, categories, pageable);
		return ResponseEntity.ok(response);
	}
}
