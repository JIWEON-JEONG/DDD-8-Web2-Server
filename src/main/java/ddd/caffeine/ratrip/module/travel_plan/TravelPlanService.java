package ddd.caffeine.ratrip.module.travel_plan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.module.travel_plan.model.TravelPlan;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanStartResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.repository.TravelPlanRepository;
import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelPlanService {

	private final TravelPlanUserService travelPlanUserService;
	private final DayScheduleService dayScheduleService;
	private final TravelPlanRepository travelPlanRepository;

	@Transactional
	public TravelPlanStartResponseDto makeTravelPlan(TravelPlan travelPlan, User user) {
		//TravelPlan 생성 및 저장
		travelPlanRepository.save(travelPlan);
		//TravelPlan 및 User 저장.
		travelPlanUserService.saveTravelPlanWithUser(travelPlan, user);
		//daySchedule 생성 및 저장.
		dayScheduleService.initTravelPlan(travelPlan, createDateList(travelPlan.getStartDate(),
			travelPlan.getTravelDays()));
		return new TravelPlanStartResponseDto(travelPlan.readUUID());
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
