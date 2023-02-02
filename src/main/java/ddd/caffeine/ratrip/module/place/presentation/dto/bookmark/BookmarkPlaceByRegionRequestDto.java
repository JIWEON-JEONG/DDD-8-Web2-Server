package ddd.caffeine.ratrip.module.place.presentation.dto.bookmark;

import ddd.caffeine.ratrip.module.place.application.dto.BookmarkPlaceByRegionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookmarkPlaceByRegionRequestDto {
	private double latitude;

	private double longitude;

	public BookmarkPlaceByRegionDto toServiceDto() {
		return BookmarkPlaceByRegionDto.of(latitude, longitude);
	}
}
