package ddd.caffeine.ratrip.module.travel_plan.presentation;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.common.validator.annotation.UUIDFormat;
import ddd.caffeine.ratrip.module.travel_plan.application.TravelPlanService;
import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlanAccessOption;
import ddd.caffeine.ratrip.module.travel_plan.domain.day_schedule.DayScheduleAccessOption;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanInitRequestDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanOngoingResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanResponseModel;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule.DayScheduleAddPlaceRequestDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule.DayScheduleAddPlaceResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule.DayScheduleExchangePlaceOrderDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule.DayScheduleInTravelPlanResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule.DayScheduleResponseDto;
import ddd.caffeine.ratrip.module.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/travel-plans")
public class TravelPlanController {

	private final TravelPlanService travelPlanService;

	@Operation(summary = "[인증] 현재 진행중인 여행 계획 정보 불러오기 API")
	@GetMapping("ongoing")
	public ResponseEntity<TravelPlanOngoingResponseDto> readTravelPlanOngoingApi(
		@Parameter(hidden = true) @AuthenticationPrincipal User user) {
		TravelPlanOngoingResponseDto response = travelPlanService.readTravelPlanByUser(user);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "[인증] 진행 했던 모든 여행계획 불러오기 - 마이페이지에서 사용예정")
	@GetMapping
	public ResponseEntity<TravelPlanResponseDto> readAllTravelPlanApi(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
		TravelPlanResponseDto response = travelPlanService.readAllTravelPlanByUser(user, pageable);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "[인증] 여행 계획 만들기 API")
	@PostMapping
	public ResponseEntity<TravelPlanResponseModel> makeTravelPlanApi(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@Valid @RequestBody TravelPlanInitRequestDto request) {
		TravelPlanResponseModel response = travelPlanService.makeTravelPlan(
			request.mapByTravelPlan(), user);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "[인증] 여행계획 중 하루일정들 모두 불러오기 API")
	@GetMapping("/{travel_plan_id}/day-schedules")
	public ResponseEntity<DayScheduleInTravelPlanResponseDto> readDaySchedulesInTravelPlanApi(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@PathVariable("travel_plan_id") @UUIDFormat String travelPlanUUID) {

		DayScheduleInTravelPlanResponseDto response = travelPlanService.readDaySchedulesInTravelPlan(
			new TravelPlanAccessOption(user, travelPlanUUID));

		return ResponseEntity.ok(response);
	}

	@Operation(summary = "[인증] 하루 일정 읽기 API")
	@GetMapping("/{travel_plan_id}/day-schedules/{day_schedule_id}")
	public ResponseEntity<DayScheduleResponseDto> readDayScheduleByUUIDApi(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@PathVariable("travel_plan_id") @UUIDFormat String travelPlanUUID,
		@PathVariable("day_schedule_id") @UUIDFormat String dayScheduleUUID,
		@RequestParam(name = "place-id", required = false) @UUIDFormat String placeUUID) {

		DayScheduleResponseDto response = travelPlanService.readScheduleByUUID(
			new DayScheduleAccessOption(user, travelPlanUUID, dayScheduleUUID), placeUUID);

		return ResponseEntity.ok(response);
	}

	/**
	 * @return : 하루 일정 UUID
	 */
	@Operation(summary = "[인증] 일정 장소 추가 API")
	@ApiResponse(description = "장소 추가 성공 시, ID 반환")
	@PostMapping("/{travel_plan_id}/day-schedules/{day_schedule_id}/places")
	public ResponseEntity<DayScheduleAddPlaceResponseDto> addPlaceInDayScheduleApi(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@PathVariable("travel_plan_id") @UUIDFormat String travelPlanUUID,
		@PathVariable("day_schedule_id") @UUIDFormat String dayScheduleUUID,
		@RequestBody DayScheduleAddPlaceRequestDto request) {
		DayScheduleAddPlaceResponseDto response = travelPlanService.addPlaceInDaySchedule(
			new DayScheduleAccessOption(user, travelPlanUUID, dayScheduleUUID),
			request.getId(), request.getMemo());

		return ResponseEntity.ok(response);
	}

	@Operation(summary = "[인증] 일정 내의 장소 순서 변경 API")
	@PatchMapping("/{travel_plan_id}/day-schedules/{day_schedule_id}/places/sequence")
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
