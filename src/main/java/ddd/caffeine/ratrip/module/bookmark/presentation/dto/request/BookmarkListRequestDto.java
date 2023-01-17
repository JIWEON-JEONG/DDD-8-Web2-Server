package ddd.caffeine.ratrip.module.bookmark.presentation.dto.request;

import static ddd.caffeine.ratrip.module.place.model.Category.*;

import java.util.HashSet;
import java.util.Set;

import ddd.caffeine.ratrip.module.place.model.Category;
import lombok.Getter;

@Getter
public class BookmarkListRequestDto {
	private Set<Category> categories = new HashSet<>();

	public BookmarkListRequestDto(Set<Category> categories) {
		if (isCategoryAll(categories)) {
			this.categories.addAll(
				Set.of(CAFE, RESTAURANT, TOURIST_ATTRACTION, PUBLIC_INSTITUTION, ACCOMMODATION, HOSPITAL, PHARMACY,
					KINDERGARTEN, SCHOOL, ACADEMY, PARKING_LOT, GAS_STATION, SUBWAY_STATION, BANK, CULTURAL_FACILITIES,
					BROKERAGE, SUPERMARKET, CONVENIENCE_STORE, ETC));
			return;
		}
		this.categories = categories;
	}

	private boolean isCategoryAll(Set<Category> categories) {
		return categories.isEmpty() || categories.contains(ALL);
	}
}
