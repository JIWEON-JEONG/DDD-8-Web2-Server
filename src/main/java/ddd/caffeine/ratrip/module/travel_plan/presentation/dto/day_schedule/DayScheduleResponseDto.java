package ddd.caffeine.ratrip.module.travel_plan.presentation.dto;

import java.util.List;
import java.util.UUID;

import ddd.caffeine.ratrip.module.travel_plan.domain.DaySchedulePlace;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DayScheduleResponseDto {

	private UUID dayScheduleUUID;
	List<DaySchedulePlace> daySchedulePlaces;
}
