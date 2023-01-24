package ddd.caffeine.ratrip.module.place.presentation.dto.search;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlaceSearchModel {
	private String id;
	private String name;
	private String address;
	private String latitude;
	private String longitude;
}
