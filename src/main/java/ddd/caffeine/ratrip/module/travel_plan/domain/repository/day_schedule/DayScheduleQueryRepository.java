package ddd.caffeine.ratrip.module.travel_plan.domain.repository.day_schedule;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import ddd.caffeine.ratrip.module.travel_plan.domain.DaySchedule;

@Repository
public interface DayScheduleQueryRepository {
	DaySchedule findByTravelPlanUUIDAndDate(UUID travelPlanUUID, LocalDate date);
}
