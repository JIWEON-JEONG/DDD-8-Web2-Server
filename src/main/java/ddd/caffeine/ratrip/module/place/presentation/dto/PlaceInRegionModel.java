package ddd.caffeine.ratrip.module.place.presentation.dto;

import ddd.caffeine.ratrip.module.place.model.Location;
import ddd.caffeine.ratrip.module.place.model.Place;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Todo : 필드 변경 될 수 있음. (와이어프레임 참고)
 */
@Getter
@NoArgsConstructor
public class PlaceInRegionModel {
	private String placeName;
	private String category;
	private String imageLink;
	private Location location;

	public PlaceInRegionModel(Place place) {
		this.placeName = place.getName();
		this.category = place.getCategory().name();
		this.imageLink = place.getImageLink();
		this.location = place.getLocation();
	}
}
