package ddd.caffeine.ratrip.module.travel_plan.application;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ddd.caffeine.ratrip.module.travel_plan.domain.repository.dao.DaySchedulePlaceDao;
import ddd.caffeine.ratrip.module.travel_plan.domain.repository.day_schedule.DaySchedulePlaceRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DaySchedulePlaceService {

	private final DaySchedulePlaceRepository daySchedulePlaceRepository;

	public List<DaySchedulePlaceDao> readDaySchedulePlaces(UUID dayScheduleUUID) {
		return daySchedulePlaceRepository.findDaySchedulePlaceDaoByDayScheduleUUID(dayScheduleUUID);
	}
}
