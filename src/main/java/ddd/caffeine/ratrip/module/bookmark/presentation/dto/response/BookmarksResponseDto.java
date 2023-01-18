package ddd.caffeine.ratrip.module.bookmark.presentation.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookmarksResponseDto {
	private final List<BookmarkPlaceDto> places;
	private final boolean hasNext;
}
