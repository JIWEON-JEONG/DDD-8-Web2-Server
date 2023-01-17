package ddd.caffeine.ratrip.module.bookmark.presentation.dto.response;

import java.util.List;

import ddd.caffeine.ratrip.module.place.model.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookmarkListResponseDto {
	private final List<Place> places;
	private final boolean hasNext;

}
