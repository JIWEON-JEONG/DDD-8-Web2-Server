package ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule;

import java.util.UUID;

import ddd.caffeine.ratrip.module.place.domain.sub_domain.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PlaceModelInDaySchedule {
	private UUID id;
	private String name;
	private Category category;

	@Builder
	public PlaceModelInDaySchedule(UUID id, String name, Category category) {
		this.id = id;
		this.name = name;
		this.category = category;
	}
}
