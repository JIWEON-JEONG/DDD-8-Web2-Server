package ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import ddd.caffeine.ratrip.module.travel_plan.domain.day_schedule.DaySchedule;
import lombok.Getter;

@Getter
public class DayScheduleInTravelPlanResponseModel {
	private UUID id;
	private LocalDate localDate;

	public DayScheduleInTravelPlanResponseModel(DaySchedule daySchedule) {
		this.id = daySchedule.readPrimaryKey();
		this.localDate = daySchedule.getDate();
	}
}
