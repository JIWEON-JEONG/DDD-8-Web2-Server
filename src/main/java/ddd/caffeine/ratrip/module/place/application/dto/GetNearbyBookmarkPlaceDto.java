package ddd.caffeine.ratrip.module.place.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetNearbyBookmarkPlaceDto {
	private final double latitude;
	private final double longitude;

	public static GetNearbyBookmarkPlaceDto of(double latitude, double longitude) {
		return new GetNearbyBookmarkPlaceDto(latitude, longitude);
	}
}
