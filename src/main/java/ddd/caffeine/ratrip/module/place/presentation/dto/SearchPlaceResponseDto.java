package ddd.caffeine.ratrip.module.place.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchPlaceResponseDto {
	private String placeName;
	private String address;
	private double latitude;
	private double longitude;
}
