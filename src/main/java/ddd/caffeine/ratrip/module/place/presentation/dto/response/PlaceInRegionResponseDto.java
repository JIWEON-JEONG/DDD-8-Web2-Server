package ddd.caffeine.ratrip.module.place.presentation.dto.response;

import java.util.ArrayList;
import java.util.List;

import ddd.caffeine.ratrip.module.place.domain.Place;
import ddd.caffeine.ratrip.module.place.presentation.dto.bookmark.BookmarkResponseDto;
import lombok.Getter;

@Getter
public class PlaceInRegionResponseDto {
	private List<PlaceInRegionResponse> places;
	private boolean hasNext;

	public PlaceInRegionResponseDto() {
		places = new ArrayList<>();
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public void addContent(Place place, BookmarkResponseDto bookmark) {
		this.places.add(new PlaceInRegionResponse(place, bookmark));
	}
}
