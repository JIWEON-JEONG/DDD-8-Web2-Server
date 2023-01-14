package ddd.caffeine.ratrip.module.bookmark.presentation;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.module.bookmark.application.BookmarkService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
	private final BookmarkService bookmarkService;

	/**
	 * POST는 데이터 값 혹은 상태를 변경하는데 사용됨
	 *
	 * @PathVariable은 resource를 식별할 때 사용되고, 정렬이나 필터링을 한다면 Query Parameter
	 */
	@PostMapping("/{placeId}")
	public ResponseEntity<UUID> addBookmark(@PathVariable UUID placeId) {
		return ResponseEntity.ok(bookmarkService.addBookmark(placeId));
	}
}
