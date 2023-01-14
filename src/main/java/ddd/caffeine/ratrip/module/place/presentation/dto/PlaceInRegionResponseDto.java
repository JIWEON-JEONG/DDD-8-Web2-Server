package ddd.caffeine.ratrip.module.place.presentation.dto;

import java.util.ArrayList;
import java.util.List;

import ddd.caffeine.ratrip.module.place.model.Place;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlaceInRegionResponseDto {
	private List<PlaceInRegionModel> placeInRegionModels;
	private boolean isEnd;

	public PlaceInRegionResponseDto(List<Place> places, boolean isEnd) {
		create(places);
		this.isEnd = isEnd;
	}

	private void create(List<Place> places) {
		this.placeInRegionModels = new ArrayList<>();
		for (Place place : places) {
			placeInRegionModels.add(new PlaceInRegionModel(place));
		}
	}
}
