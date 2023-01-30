package ddd.caffeine.ratrip.module.travel_plan.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.module.place.application.PlaceService;
import ddd.caffeine.ratrip.module.place.domain.Place;
import ddd.caffeine.ratrip.module.travel_plan.application.day_schedule.DayScheduleService;
import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlan;
import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlanAccessOption;
import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlanUser;
import ddd.caffeine.ratrip.module.travel_plan.domain.day_schedule.DaySchedule;
import ddd.caffeine.ratrip.module.travel_plan.domain.day_schedule.DayScheduleAccessOption;
import ddd.caffeine.ratrip.module.travel_plan.domain.repository.TravelPlanRepository;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanOngoingResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanResponseModel;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule.DayScheduleInTravelPlanResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule.DaySchedulePlaceResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule.DayScheduleResponseDto;
import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelPlanService {
	private final TravelPlanUserService travelPlanUserService;
	private final TravelPlanValidator travelPlanValidator;
	private final DayScheduleService dayScheduleService;
	private final PlaceService placeService;
	private final TravelPlanRepository travelPlanRepository;

	@Transactional(readOnly = true)
	public TravelPlanOngoingResponseDto readTravelPlanByUser(User user) {
		TravelPlanUser travelPlanUser = travelPlanUserService.readByUserUnfinishedTravel(user);
		//작성중인 여행 없을 경우,
		if (travelPlanUser == null) {
			return new TravelPlanOngoingResponseDto(Boolean.FALSE);
		}
		//작성중인 여행이 있을 경우,
		return TravelPlanOngoingResponseDto.builder()
			.content(new TravelPlanResponseModel(travelPlanUser.readTravelPlan()))
			.hasPlan(Boolean.TRUE)
			.build();
	}

	@Transactional(readOnly = true)
	public TravelPlanResponseDto readAllTravelPlanByUser(User user, Pageable pageable) {
		return travelPlanUserService.readByUser(user, pageable);
	}

	@Transactional
	public TravelPlanResponseModel makeTravelPlan(TravelPlan travelPlan, User user) {
		//진행중인 일정 있을 경우 예외
		travelPlanUserService.validateMakeTravelPlan(user);
		//TravelPlan 생성 및 저장
		travelPlanRepository.save(travelPlan);
		//TravelPlan 및 User 저장.
		travelPlanUserService.saveTravelPlanWithUser(travelPlan, user);
		//daySchedule 생성 및 저장.
		dayScheduleService.initTravelPlan(travelPlan, createDateList(travelPlan.getStartDate(),
			travelPlan.getTravelDays()));
		return new TravelPlanResponseModel(travelPlan);
	}

	@Transactional
	public TravelPlanResponseModel endTravelPlan(TravelPlanAccessOption accessOption) {
		//접근 가능한 유저인지 확인
		travelPlanUserService.validateAccessTravelPlan(accessOption);
		//객체 가져오기
		TravelPlan travelPlan = travelPlanValidator.validateExistTravelPlan(
			travelPlanRepository.findById(accessOption.readTravelPlanUUID()));
		//변경
		travelPlan.endTheTrip();
		return new TravelPlanResponseModel(travelPlan);
	}

	@Transactional(readOnly = true)
	public DayScheduleResponseDto readScheduleByUUID(DayScheduleAccessOption accessOption, String placeUUID) {
		//접근 가능한 유저인지 확인
		travelPlanUserService.validateAccessTravelPlan(accessOption.readTravelPlanAccessOption());
		return dayScheduleService.readDaySchedule(accessOption.readDayScheduleUUID(), placeUUID);
	}

	@Transactional(readOnly = true)
	public DayScheduleInTravelPlanResponseDto readDaySchedulesInTravelPlan(TravelPlanAccessOption accessOption) {
		//접근 가능한 유저인지 확인
		travelPlanUserService.validateAccessTravelPlan(accessOption);
		List<DaySchedule> daySchedules = dayScheduleService.readDaySchedulesInTravelPlan(
			accessOption.readTravelPlanUUID());

		return new DayScheduleInTravelPlanResponseDto(daySchedules);
	}

	@Transactional
	public DaySchedulePlaceResponseDto addPlaceInDaySchedule(DayScheduleAccessOption accessOption, String placeUUID,
		String memo) {
		//접근 가능한 유저인지 확인
		travelPlanUserService.validateAccessTravelPlan(accessOption.readTravelPlanAccessOption());
		//장소 불러오기
		Place place = placeService.readPlaceByUUID(UUID.fromString(placeUUID));
		//저장하기
		UUID daySchedulePlaceUUID = dayScheduleService.addPlace(accessOption.readDayScheduleUUID(), place, memo);

		return new DaySchedulePlaceResponseDto(daySchedulePlaceUUID);
	}

	@Transactional
	public DaySchedulePlaceResponseDto updatePlaceInDaySchedule(DayScheduleAccessOption accessOption,
		String daySchedulePlaceUUID, String memo) {
		//접근 가능한 유저인지 확인
		travelPlanUserService.validateAccessTravelPlan(accessOption.readTravelPlanAccessOption());
		//업데이트
		UUID updatedDaySchedulePlaceUUID = dayScheduleService.updateDaySchedulePlace(daySchedulePlaceUUID, memo);
		return new DaySchedulePlaceResponseDto(updatedDaySchedulePlaceUUID);
	}

	@Transactional
	public void deletePlaceInDaySchedule(DayScheduleAccessOption accessOption, String daySchedulePlaceUUID) {
		//접근 가능한 유저인지 확인
		travelPlanUserService.validateAccessTravelPlan(accessOption.readTravelPlanAccessOption());
		//삭제
		dayScheduleService.deleteDaySchedulePlace(daySchedulePlaceUUID);
	}

	@Transactional
	public void exchangePlaceSequenceInDaySchedule(DayScheduleAccessOption accessOption,
		List<UUID> daySchedulePlaceUUIDs) {
		//접근 가능한 유저인지 확인
		travelPlanUserService.validateAccessTravelPlan(accessOption.readTravelPlanAccessOption());
		//하루 일정 장소 순서 exchange
		dayScheduleService.exchangePlaceSequence(daySchedulePlaceUUIDs);
	}

	private List<LocalDate> createDateList(LocalDate startTravelDate, int travelDays) {
		List<LocalDate> dates = new ArrayList<>();
		for (int i = 0; i < travelDays; i++) {
			LocalDate localDate = startTravelDate.plusDays(i);
			dates.add(localDate);
		}
		return dates;
	}
}
