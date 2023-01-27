package ddd.caffeine.ratrip.module.place.presentation.dto.bookmark;

import javax.validation.constraints.NotBlank;

import ddd.caffeine.ratrip.module.place.application.dto.BookmarkPlaceByRegionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookmarkPlaceByRegionRequestDto {
	@NotBlank(message = "Latitude must not be blank")
	private double latitude;

	@NotBlank(message = "Longitude must not be blank")
	private double longitude;

	public BookmarkPlaceByRegionDto toServiceDto() {
		return BookmarkPlaceByRegionDto.of(latitude, longitude);
	}
}
