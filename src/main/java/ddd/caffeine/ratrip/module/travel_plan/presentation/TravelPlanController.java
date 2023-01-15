package ddd.caffeine.ratrip.module.travel_plan.presentation;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.module.travel_plan.TravelPlanService;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanStartRequestDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanStartResponseDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/travel-plan")
public class TravelPlanController {

	private final TravelPlanService travelPlanService;

	@PostMapping
	public ResponseEntity<TravelPlanStartResponseDto> makeTravelPlanApi
		(@Valid @RequestBody TravelPlanStartRequestDto request) {
		travelPlanService.makeTravelPlan(request.mapByTravelPlan());
		return ResponseEntity.ok(new TravelPlanStartResponseDto());
	}
}
