package ddd.caffeine.ratrip.module.place.presentation.dto;

import java.util.ArrayList;
import java.util.List;

import ddd.caffeine.ratrip.module.place.model.Place;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PopularPlaceResponse {
	List<PopularPlaceModel> popularPlaceModels;

	public PopularPlaceResponse(List<Place> places) {
		create(places);
	}

	private void create(List<Place> places) {
		this.popularPlaceModels = new ArrayList<>();
		for (Place place : places) {
			popularPlaceModels.add(new PopularPlaceModel(place));
		}
	}
}
