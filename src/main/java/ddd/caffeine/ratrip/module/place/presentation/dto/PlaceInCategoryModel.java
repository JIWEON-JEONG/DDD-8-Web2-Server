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
	private String category; //TODO - String으로만 하는게 맞나?

	public PlaceInCategoryModel(Place place) {
		this.placeName = place.getName();
		this.detailAddress = place.getAddress().getDetailed();
		this.imageLink = place.getImageLink();
		this.category = place.getCategory().getCode();
	}
}
