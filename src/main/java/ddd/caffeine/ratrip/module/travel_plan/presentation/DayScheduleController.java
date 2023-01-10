package ddd.caffeine.ratrip.module.travel_plan.presentation;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.module.travel_plan.DayScheduleService;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanStartRequestDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanStartResponseDto;
import lombok.RequiredArgsConstructor;

/**
 * 장소 추천 API
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/schedule")
public class DayScheduleController {

	private DayScheduleService dayScheduleService;

	@GetMapping
	public ResponseEntity<TravelPlanStartResponseDto> makeTravelPlanApi
		(@Valid @RequestBody TravelPlanStartRequestDto request) {
		return ResponseEntity.ok(new TravelPlanStartResponseDto());
	}
}
