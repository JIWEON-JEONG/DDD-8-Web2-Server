package ddd.caffeine.ratrip.module.place.presentation.dto.bookmark;

import java.util.List;

import ddd.caffeine.ratrip.module.place.domain.repository.dao.BookMarkPlaceDao;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookmarksResponseDto {
	private final List<BookMarkPlaceDao> places;
	private final boolean hasNext;
}
