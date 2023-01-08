package ddd.caffeine.ratrip.module.place.presentation.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlaceSearchModel {
	private String placeKakaoId;
	private String placeName;
	private String address;
	private String latitude;
	private String longitude;
}
