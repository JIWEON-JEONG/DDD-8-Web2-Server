package ddd.caffeine.ratrip.module.travel_plan;

import org.springframework.stereotype.Service;

import ddd.caffeine.ratrip.module.travel_plan.model.TravelPlan;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanStartRequestDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanStartResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.repository.TravelPlanRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelPlanService {

	private final TravelPlanUserService travelPlanUserService;
	private final DayScheduleService dayScheduleService;
	private final TravelPlanRepository travelPlanRepository;

	public TravelPlanStartResponseDto makeTravelPlan(TravelPlanStartRequestDto planStartRequestDto) {
		//TravelPlan 생성 및 저장
		TravelPlan travelPlan = planStartRequestDto.mapByTravelPlan();
		travelPlanRepository.save(travelPlan);
		//TravelPlan 및 User 저장.
		//daySchedule 생성 및 저장.
		// dayScheduleService.initTravelPlan(travelPlan, readTravelPlanDates(planStartRequestDto.getTravelDates()));
		return new TravelPlanStartResponseDto();
	}

	// private List<LocalDate> readTravelPlanDates(List<TravelDate> travelDates) {
	// 	List<LocalDate> dates = new ArrayList<>();
	// 	for (TravelDate travelDate : travelDates) {
	// 		dates.add(travelDate.getDate());
	// 	}
	// 	return dates;
	// }

}
