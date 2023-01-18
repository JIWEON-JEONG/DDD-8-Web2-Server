package ddd.caffeine.ratrip.module.travel_plan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.module.travel_plan.model.DaySchedule;
import ddd.caffeine.ratrip.module.travel_plan.model.TravelPlan;
import ddd.caffeine.ratrip.module.travel_plan.repository.DayScheduleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DayScheduleService {

	private final DayScheduleRepository dayScheduleRepository;

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
