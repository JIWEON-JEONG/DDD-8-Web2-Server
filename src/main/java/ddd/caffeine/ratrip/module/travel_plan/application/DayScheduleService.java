package ddd.caffeine.ratrip.module.travel_plan.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.module.travel_plan.application.DaySchedulePlaceService;
import ddd.caffeine.ratrip.module.travel_plan.domain.DaySchedule;
import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlan;
import ddd.caffeine.ratrip.module.travel_plan.domain.repository.DayScheduleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DayScheduleService {

	private final DayScheduleRepository dayScheduleRepository;
	private final DaySchedulePlaceService daySchedulePlaceService;

	@Transactional
	public void initTravelPlan(TravelPlan travelPlan, List<LocalDate> dates) {
		List<DaySchedule> daySchedules = new ArrayList<>();
		for (LocalDate date : dates) {
			DaySchedule daySchedule = DaySchedule.builder()
				.travelPlan(travelPlan)
				.date(date)
				.build();
			daySchedules.add(daySchedule);
		}
		dayScheduleRepository.saveAll(daySchedules);
	}
}
