package ddd.caffeine.ratrip.module.travel_plan;

import org.springframework.stereotype.Service;

import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanStartRequestDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanStartResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelPlanService {

	public TravelPlanStartResponseDto makeTravelPlan(TravelPlanStartRequestDto planStartRequestDto) {
		return new TravelPlanStartResponseDto();
	}
}
