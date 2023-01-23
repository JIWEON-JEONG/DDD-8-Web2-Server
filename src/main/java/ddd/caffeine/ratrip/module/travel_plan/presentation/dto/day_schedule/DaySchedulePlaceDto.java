package ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule;

import java.util.UUID;

import ddd.caffeine.ratrip.module.place.domain.Category;
import ddd.caffeine.ratrip.module.travel_plan.domain.repository.dao.DaySchedulePlaceDao;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DaySchedulePlaceDto {
	private UUID placeUUID;
	private String placeName;
	private Category category;
	private String memo;
	private int order;

	@Builder
	public DaySchedulePlaceDto(DaySchedulePlaceDao model) {
		this.placeUUID = model.getPlaceUUID();
		this.placeName = model.getPlaceName();
		this.category = model.getCategory();
		this.memo = model.getMemo();
		this.order = model.getOrder();
	}
}
