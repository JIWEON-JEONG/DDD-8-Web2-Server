package ddd.caffeine.ratrip.module.travel_plan.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlan;
import ddd.caffeine.ratrip.module.travel_plan.domain.repository.TravelPlanRepository;
import ddd.caffeine.ratrip.module.travel_plan.domain.repository.dao.LocalDateDao;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanInitResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule.DayScheduleResponseDto;
import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelPlanService {
	private final TravelPlanUserService travelPlanUserService;
	private final DayScheduleService dayScheduleService;
	private final TravelPlanRepository travelPlanRepository;

	@Transactional(readOnly = true)
	public TravelPlanResponseDto readTravelPlanByUser(User user) {
		return travelPlanUserService.readByUserUnfinishedTravel(user);
	}

	@Transactional
	public TravelPlanInitResponseDto makeTravelPlan(TravelPlan travelPlan, User user) {
		//TravelPlan 생성 및 저장
		travelPlanRepository.save(travelPlan);
		//TravelPlan 및 User 저장.
		travelPlanUserService.saveTravelPlanWithUser(travelPlan, user);
		//daySchedule 생성 및 저장.
		dayScheduleService.initTravelPlan(travelPlan, createDateList(travelPlan.getStartDate(),
			travelPlan.getTravelDays()));
		return new TravelPlanInitResponseDto(travelPlan);
	}

	@Transactional(readOnly = true)
	public DayScheduleResponseDto readScheduleByDay(User user, String travelPlanUUID, int day) {
		//접근 가능한 유저인지 확인
		travelPlanUserService.validateAccessTravelPlan(user, travelPlanUUID);
		LocalDateDao startDate = travelPlanRepository.findLocalDateById(UUID.fromString(travelPlanUUID));
		LocalDate date = startDate.getLocalDate().plusDays(day - 1);

		return dayScheduleService.readDaySchedule(UUID.fromString(travelPlanUUID), date);
	}

	@Transactional
	public void exchangePlaceOrderInDaySchedule(User user, String travelPlanUUID, String dayScheduleUUID,
		String firstPlaceUUID, String secondPlaceUUID) {
		//접근 가능한 유저인지 확인
		travelPlanUserService.validateAccessTravelPlan(user, travelPlanUUID);
		//하루 일정 장소 순서 exchange
		dayScheduleService.exchangePlaceOrder(dayScheduleUUID, firstPlaceUUID, secondPlaceUUID);
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
