package ddd.caffeine.ratrip.module.travel_plan.domain.repository.dao;

import java.util.UUID;

import com.querydsl.core.annotations.QueryProjection;

import ddd.caffeine.ratrip.module.place.domain.sub_domain.Category;
import lombok.Getter;

@Getter
public class DaySchedulePlaceDao {
	private UUID placeUUID;
	private String placeName;
	private Category category;
	private String memo;
	private int sequence;

	@QueryProjection
	public DaySchedulePlaceDao(UUID placeUUID, String placeName, Category category,
		String memo, int sequence) {
		this.placeUUID = placeUUID;
		this.placeName = placeName;
		this.category = category;
		this.memo = memo;
		this.sequence = sequence;
	}
}
