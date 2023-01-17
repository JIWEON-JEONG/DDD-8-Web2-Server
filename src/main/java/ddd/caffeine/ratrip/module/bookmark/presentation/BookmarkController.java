package ddd.caffeine.ratrip.module.bookmark.presentation;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.module.bookmark.application.BookmarkService;
import ddd.caffeine.ratrip.module.place.presentation.dto.PlaceInCategoryResponseDto;
import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
	private final BookmarkService bookmarkService;

	@GetMapping("")
	public ResponseEntity<PlaceInCategoryResponseDto> getBookmarks(@AuthenticationPrincipal User user,
		@RequestParam(name = "category", required = false) List<String> categories,
		//TODO - 인자를 Enum 타입으로 받는 법 알아보기
		@PageableDefault(size = 3, sort = "name", direction = Sort.Direction.DESC) Pageable pageable) { //TODO - direction = Sort.Direction.DESC이 뭐지
		return ResponseEntity.ok(bookmarkService.getBookmarks(user, categories, pageable));
	}

	@GetMapping("/{placeId}") //TODO - 삭제해야 됨
	public ResponseEntity<Boolean> isBookmarked(@PathVariable UUID placeId, @AuthenticationPrincipal User user) {
		return ResponseEntity.ok(bookmarkService.isBookmarked(placeId, user));
	}

	@PostMapping("/{placeId}")
	public ResponseEntity<UUID> addBookmark(@PathVariable UUID placeId, @AuthenticationPrincipal User user) {
		return ResponseEntity.ok(bookmarkService.addBookmark(placeId, user));
	}

	@DeleteMapping("/{placeId}")
	public ResponseEntity<Void> deleteBookmark(@PathVariable UUID placeId, @AuthenticationPrincipal User user) {
		bookmarkService.deleteBookmark(placeId, user);
		return ResponseEntity.ok().build();
	}

	//헤더 스웨거 추가
}
