package ddd.caffeine.ratrip.module.travel_plan.presentation.dto;

import java.time.LocalDate;
import java.util.UUID;

import ddd.caffeine.ratrip.common.model.Region;
import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlan;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TravelPlanResponseDto {

	private UUID id;

	private String title;

	private Region region;

	private LocalDate startDate;

	private int travelDays;

	private boolean hasPlan;

	public TravelPlanResponseDto(boolean hasPlan) {
		this.hasPlan = hasPlan;
	}

	@Builder
	public TravelPlanResponseDto(TravelPlan travelPlan, boolean hasPlan) {
		this.id = travelPlan.readUUID();
		this.title = travelPlan.getTitle();
		this.region = travelPlan.getRegion();
		this.startDate = travelPlan.getStartDate();
		this.travelDays = travelPlan.getTravelDays();
		this.hasPlan = hasPlan;
	}
}
