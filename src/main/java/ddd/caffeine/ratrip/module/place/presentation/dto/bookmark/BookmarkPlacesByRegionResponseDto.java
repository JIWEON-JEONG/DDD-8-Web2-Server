package ddd.caffeine.ratrip.module.place.presentation.dto.bookmark;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookmarkPlacesByRegionResponseDto {
	List<BookmarkPlaceByRegionDao> places;
}
