package ddd.caffeine.ratrip.module.place.presentation.dto;

import java.util.ArrayList;
import java.util.List;

import ddd.caffeine.ratrip.module.place.model.Place;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PlaceInCategoryResponseDto {
	private List<PlaceInCategoryModel> placeInCategoryModels;
	private boolean hasNext;

	public PlaceInCategoryResponseDto(List<Place> places, boolean hasNext) {
		initPlaceInCategoryModels(places);
		this.hasNext = hasNext;
	}

	private void initPlaceInCategoryModels(List<Place> places) {
		this.placeInCategoryModels = new ArrayList<>();
		for (Place place : places) {
			placeInCategoryModels.add(new PlaceInCategoryModel(place));
		}
	}
}
