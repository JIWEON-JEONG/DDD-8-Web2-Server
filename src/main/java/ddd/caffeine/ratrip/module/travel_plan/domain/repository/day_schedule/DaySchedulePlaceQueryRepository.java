package ddd.caffeine.ratrip.module.travel_plan.domain.repository.day_schedule;

import java.util.List;
import java.util.UUID;

import ddd.caffeine.ratrip.module.travel_plan.domain.repository.dao.DaySchedulePlaceDao;

public interface DaySchedulePlaceQueryRepository {
	List<DaySchedulePlaceDao> findDaySchedulePlaceDaoByDayScheduleUUID(UUID dayScheduleUUID);
}
