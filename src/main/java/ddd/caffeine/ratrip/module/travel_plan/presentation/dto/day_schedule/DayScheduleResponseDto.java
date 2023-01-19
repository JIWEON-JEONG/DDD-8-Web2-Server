package ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DayScheduleResponseDto {
	private UUID dayScheduleUUID;
	List<DaySchedulePlaceDto> daySchedulePlaces;

	@Builder
	public DayScheduleResponseDto(UUID dayScheduleUUID, List<DaySchedulePlaceDto> daySchedulePlaces) {
		this.dayScheduleUUID = dayScheduleUUID;
		this.daySchedulePlaces = daySchedulePlaces;
	}
}
