package ddd.caffeine.ratrip.module.place.presentation.dto.bookmark;

import ddd.caffeine.ratrip.module.place.domain.bookmark.Bookmark;
import lombok.Getter;

@Getter
public class BookmarkResponseDto {
	private String id;
	private boolean isBookmarked;

	public BookmarkResponseDto(Bookmark bookmark) {
		this.isBookmarked = bookmark.isActivated();
	}

	public BookmarkResponseDto(String id, boolean isBookmarked) {
		this.id = id;
		this.isBookmarked = isBookmarked;
	}
}
