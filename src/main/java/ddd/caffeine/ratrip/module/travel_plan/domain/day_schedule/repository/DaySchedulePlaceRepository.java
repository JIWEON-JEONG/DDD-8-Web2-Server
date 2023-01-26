package ddd.caffeine.ratrip.module.travel_plan.domain.day_schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ddd.caffeine.ratrip.module.travel_plan.domain.day_schedule.DaySchedulePlace;

@Repository
public interface DaySchedulePlaceRepository
	extends JpaRepository<DaySchedulePlace, Long>, DaySchedulePlaceQueryRepository {
}
