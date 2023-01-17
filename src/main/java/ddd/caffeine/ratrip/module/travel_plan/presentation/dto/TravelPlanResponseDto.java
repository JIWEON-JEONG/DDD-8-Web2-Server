package ddd.caffeine.ratrip.module.travel_plan.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TravelPlanResponseDto {

	private boolean hasPlan = Boolean.TRUE;

	@Builder
	public TravelPlanResponseDto(boolean hasPlan) {
		this.hasPlan = hasPlan;
	}
}
