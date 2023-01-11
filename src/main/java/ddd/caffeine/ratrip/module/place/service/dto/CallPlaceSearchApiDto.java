package ddd.caffeine.ratrip.module.place.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CallPlaceSearchApiDto {
	private final String keyword;
	private final String latitude;
	private final String longitude;
	private final int page;

	public static CallPlaceSearchApiDto of(String keyword, String latitude, String longitude, int page) {
		return new CallPlaceSearchApiDto(keyword, latitude, longitude, page);
	}
}
