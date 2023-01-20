package ddd.caffeine.ratrip.module.travel_plan.presentation;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.common.validator.annotation.UUIDFormat;
import ddd.caffeine.ratrip.module.travel_plan.application.TravelPlanService;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanInitRequestDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanInitResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule.DayScheduleResponseDto;
import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/travel-plans")
public class TravelPlanController {

	private final TravelPlanService travelPlanService;

	@GetMapping("ongoing")
	public ResponseEntity<TravelPlanResponseDto> readTravelPlanOngoingApi(
		@AuthenticationPrincipal User user) {
		TravelPlanResponseDto response = travelPlanService.readTravelPlanByUser(user);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<TravelPlanInitResponseDto> makeTravelPlanApi
		(@AuthenticationPrincipal User user,
			@Valid @RequestBody TravelPlanInitRequestDto request) {
		TravelPlanInitResponseDto response = travelPlanService.makeTravelPlan(
			request.mapByTravelPlan(), user);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}/day-schedules")
	public ResponseEntity<DayScheduleResponseDto> ReadScheduleByDayApi
		(@AuthenticationPrincipal User user,
			@PathVariable("id") @UUIDFormat String travelPlanUUID,
			@RequestParam(defaultValue = "1", required = false) @Min(1) int day) {

		DayScheduleResponseDto response = travelPlanService.readScheduleByDay(user, travelPlanUUID, day);
		return ResponseEntity.ok(response);
	}

}
