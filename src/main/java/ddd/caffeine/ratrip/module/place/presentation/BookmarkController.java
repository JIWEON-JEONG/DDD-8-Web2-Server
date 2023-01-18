package ddd.caffeine.ratrip.module.place.presentation;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.module.place.application.BookmarkService;
import ddd.caffeine.ratrip.module.bookmark.presentation.dto.response.BookmarksResponseDto;
import ddd.caffeine.ratrip.module.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
	private final BookmarkService bookmarkService;

	@Operation(summary = "[인증] 북마크 리스트 조회")
	@GetMapping("")
	public ResponseEntity<BookmarksResponseDto> getBookmarks(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@RequestParam(name = "category", required = false) List<String> categories,
		//TODO - 인자를 Enum 타입으로 받는 법 알아보기
		@PageableDefault(size = 7, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
		return ResponseEntity.ok(bookmarkService.getBookmarks(user, categories, pageable));
	}
}
