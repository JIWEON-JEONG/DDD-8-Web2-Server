package ddd.caffeine.ratrip.module.travel_plan.presentation;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.common.validator.annotation.UUID;
import ddd.caffeine.ratrip.module.travel_plan.TravelPlanService;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanStartRequestDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanStartResponseDto;
import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/travel-plan")
public class TravelPlanController {

	private final TravelPlanService travelPlanService;

	@GetMapping("/{id}")
	public ResponseEntity<TravelPlanResponseDto> readTravelPlanApi
		(@AuthenticationPrincipal User user, @PathVariable @UUID @NotEmpty String id) {

		return ResponseEntity.ok(new TravelPlanResponseDto());
	}

	@PostMapping
	public ResponseEntity<TravelPlanStartResponseDto> makeTravelPlanApi
		(@AuthenticationPrincipal User user, @Valid @RequestBody TravelPlanStartRequestDto request) {
		TravelPlanStartResponseDto response = travelPlanService.makeTravelPlan(
			request.mapByTravelPlan(), user);
		return ResponseEntity.ok(response);
	}

	@GetMapping("v1/travel-plan/{id}/day-schedule")
	public ResponseEntity<TravelPlanResponseDto> ReadScheduleByDayApi
		(@AuthenticationPrincipal User user,
			@PathVariable("id") @UUID @NotEmpty String travelPlanUUID,
			@RequestParam(defaultValue = "1") @Min(1) int day) {

		return ResponseEntity.ok(new TravelPlanResponseDto());
	}

}
