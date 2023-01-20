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
import ddd.caffeine.ratrip.module.travel_plan.domain.DayScheduleAccessOption;
import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlanAccessOption;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanInitRequestDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanInitResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule.DayScheduleAddPlaceRequestDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule.DayScheduleAddPlaceResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule.DayScheduleExchangePlaceOrderDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule.DayScheduleResponseDto;
import ddd.caffeine.ratrip.module.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/travel-plans")
public class TravelPlanController {

	private final TravelPlanService travelPlanService;

	@Operation(summary = "현재 진행중인 여행 계획 정보 불러오기 API")
	@GetMapping("ongoing")
	public ResponseEntity<TravelPlanResponseDto> readTravelPlanOngoingApi(
		@Parameter(hidden = true) @AuthenticationPrincipal User user) {
		TravelPlanResponseDto response = travelPlanService.readTravelPlanByUser(user);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "여행 계획 만들기 API")
	@PostMapping
	public ResponseEntity<TravelPlanInitResponseDto> makeTravelPlanApi(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@Valid @RequestBody TravelPlanInitRequestDto request) {

		TravelPlanInitResponseDto response = travelPlanService.makeTravelPlan(
			request.mapByTravelPlan(), user);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "하루 일정 읽기 API")
	@GetMapping("/{travel_plan_id}/day-schedules")
	public ResponseEntity<DayScheduleResponseDto> ReadScheduleByDayApi(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@PathVariable("travel_plan_id") @UUIDFormat String travelPlanUUID,
		@RequestParam(defaultValue = "1", required = false) @Min(1) int day) {

		DayScheduleResponseDto response = travelPlanService.readScheduleByDay(
			new TravelPlanAccessOption(user, travelPlanUUID), day);

		return ResponseEntity.ok(response);
	}

	/**
	 * @return : 하루 일정 UUID
	 */
	@Operation(summary = "일정 장소 추가 API")
	@PostMapping("/{travel_plan_id}/day-schedules/{day_schedule_id}/places")
	public ResponseEntity<DayScheduleAddPlaceResponseDto> addPlaceInDayScheduleApi(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@PathVariable("travel_plan_id") @UUIDFormat String travelPlanUUID,
		@PathVariable("day_schedule_id") @UUIDFormat String dayScheduleUUID,
		@RequestBody DayScheduleAddPlaceRequestDto request) {
		DayScheduleAddPlaceResponseDto response = travelPlanService.addPlaceInDaySchedule(
			new DayScheduleAccessOption(user, travelPlanUUID, dayScheduleUUID),
			request.getPlaceUUID(), request.getMemo());

		return ResponseEntity.ok(response);
	}

	@Operation(summary = "일정 내의 장소 순서 변경 API")
	@PostMapping("/{travel_plan_id}/day-schedules/{day_schedule_id}/places/sequence")
	public ResponseEntity<String> exchangePlaceSequenceInDayScheduleApi(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@PathVariable("travel_plan_id") @UUIDFormat String travelPlanUUID,
		@PathVariable("day_schedule_id") @UUIDFormat String dayScheduleUUID,
		@RequestBody DayScheduleExchangePlaceOrderDto request) {

		travelPlanService.exchangePlaceSequenceInDaySchedule(
			new DayScheduleAccessOption(user, travelPlanUUID, dayScheduleUUID),
			request.readPlaceUUIDs());
		return ResponseEntity.ok("SUCCESS TO EXCHANGE");
	}
}
