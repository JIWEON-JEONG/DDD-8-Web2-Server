package ddd.caffeine.ratrip.module.place.presentation.dto;

import ddd.caffeine.ratrip.module.place.model.Place;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PlaceInCategoryModel {
	private String placeName;
	private String detailAddress;
	private String imageLink;

	public PlaceInCategoryModel(Place place) {
		this.placeName = place.getName();
		this.detailAddress = place.getAddress().getDetailed();
		this.imageLink = place.getImageLink();
	}
}
