package ddd.caffeine.ratrip.module.travel_plan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.module.place.service.PlaceService;
import ddd.caffeine.ratrip.module.travel_plan.model.TravelPlan;
import ddd.caffeine.ratrip.module.travel_plan.model.TravelPlanUser;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanInitResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.repository.TravelPlanRepository;
import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelPlanService {

	private final PlaceService placeService;
	private final TravelPlanUserService travelPlanUserService;
	private final DayScheduleService dayScheduleService;
	private final TravelPlanRepository travelPlanRepository;

	@Transactional(readOnly = true)
	public TravelPlanResponseDto readTravelPlanByUser(User user) {
		Optional<TravelPlanUser> travelPlanUser = travelPlanUserService.readByUserUnfinishedTravel(user);
		//작성중인 여행이 없을 경우,
		if (travelPlanUser.isEmpty()) {
			return TravelPlanResponseDto.builder().hasPlan(Boolean.FALSE).build();
		}
		//작성중인 여행이 있을 경우,

		return new TravelPlanResponseDto();
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
	public TravelPlanInitResponseDto readScheduleByDay(User user, String travelPlanUUID, int day) {
		//이 유저가 여행을 읽을 수 있는지 검증
		travelPlanUserService.validateAccessTravelPlan(user, travelPlanUUID);
		//
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
