package ddd.caffeine.ratrip.module.travel_plan.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TravelPlanOngoingResponseDto {

	private TravelPlanResponseModel content;
	private boolean hasPlan;

	public TravelPlanOngoingResponseDto(boolean hasPlan) {
		this.hasPlan = hasPlan;
	}

	@Builder
	public TravelPlanOngoingResponseDto(TravelPlanResponseModel content, boolean hasPlan) {
		this.content = content;
		this.hasPlan = hasPlan;
	}
}
