package ddd.caffeine.ratrip.module.travel_plan.presentation.dto;

import java.util.UUID;

import lombok.Getter;

@Getter
public class TravelPlanStartResponseDto {
	private UUID planUUID;

	public TravelPlanStartResponseDto(UUID planUUID) {
		this.planUUID = planUUID;
	}
}
