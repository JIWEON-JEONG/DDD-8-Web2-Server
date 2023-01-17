package ddd.caffeine.ratrip.module.travel_plan.presentation.dto;

import java.time.LocalDate;

import ddd.caffeine.ratrip.module.travel_plan.model.TravelPlan;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TravelPlanResponseDto {
	private String title;
	private LocalDate startDate;
	private int travelDays;

	public TravelPlanResponseDto(TravelPlan travelPlan) {
		this.title = travelPlan.getTitle();
		this.startDate = travelPlan.getStartDate();
		this.travelDays = travelPlan.getTravelDays();
	}
}
