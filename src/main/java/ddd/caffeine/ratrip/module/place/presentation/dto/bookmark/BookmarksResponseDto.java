package ddd.caffeine.ratrip.module.place.presentation.dto.bookmark;

import java.util.List;

import ddd.caffeine.ratrip.module.place.domain.repository.dao.BookmarkPlaceDao;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookmarksResponseDto {
	private final List<BookmarkPlaceDao> places;
	private final boolean hasNext;
}
