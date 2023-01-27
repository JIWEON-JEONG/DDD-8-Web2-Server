package ddd.caffeine.ratrip.module.place.presentation.dto.bookmark;

import javax.validation.constraints.NotBlank;

import ddd.caffeine.ratrip.module.place.application.dto.GetNearbyBookmarkPlaceDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GetNearbyBookmarkPlaceRequestDto {
	@NotBlank(message = "Latitude must not be blank")
	private double latitude;

	@NotBlank(message = "Longitude must not be blank")
	private double longitude;

	public GetNearbyBookmarkPlaceDto toServiceDto() {
		return GetNearbyBookmarkPlaceDto.of(latitude, longitude);
	}
}
