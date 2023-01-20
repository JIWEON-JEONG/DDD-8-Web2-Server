package ddd.caffeine.ratrip.module.travel_plan.presentation;

import java.util.UUID;

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
import ddd.caffeine.ratrip.module.travel_plan.domain.DayScheduleAccessOption;
import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlanAccessOption;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanInitRequestDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanInitResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule.DayScheduleAddPlaceDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule.DayScheduleExchangePlaceOrderDto;
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

	@GetMapping("/{travel_plan_id}/day-schedules")
	public ResponseEntity<DayScheduleResponseDto> ReadScheduleByDayApi
		(@AuthenticationPrincipal User user,
			@PathVariable("travel_plan_id") @UUIDFormat String travelPlanUUID,
			@RequestParam(defaultValue = "1", required = false) @Min(1) int day) {

		DayScheduleResponseDto response = travelPlanService.readScheduleByDay(
			new TravelPlanAccessOption(user, travelPlanUUID), day);

		return ResponseEntity.ok(response);
	}

	/**
	 * @return : 하루 일정 UUID
	 */
	@PostMapping("/{travel_plan_id}/day-schedules/{day_schedule_id}/places")
	public ResponseEntity<UUID> addPlaceInDayScheduleApi
		(@AuthenticationPrincipal User user,
			@PathVariable("travel_plan_id") @UUIDFormat String travelPlanUUID,
			@PathVariable("day_schedule_id") @UUIDFormat String dayScheduleUUID,
			@RequestBody DayScheduleAddPlaceDto request) {
		UUID response = travelPlanService.addPlaceInDaySchedule(
			new DayScheduleAccessOption(user, travelPlanUUID, dayScheduleUUID),
			request.getPlaceUUID(), request.getMemo());

		return ResponseEntity.ok(response);
	}

	@PostMapping("/{travel_plan_id}/day-schedules/{day_schedule_id}/places/sequence")
	public ResponseEntity<String> exchangePlaceSequenceInDayScheduleApi
		(@AuthenticationPrincipal User user,
			@PathVariable("travel_plan_id") @UUIDFormat String travelPlanUUID,
			@PathVariable("day_schedule_id") @UUIDFormat String dayScheduleUUID,
			@RequestBody DayScheduleExchangePlaceOrderDto request) {

		travelPlanService.exchangePlaceSequenceInDaySchedule(
			new DayScheduleAccessOption(user, travelPlanUUID, dayScheduleUUID),
			request.readPlaceUUIDs());
		return ResponseEntity.ok("SUCCESS TO EXCHANGE");
	}
}
