package ddd.caffeine.ratrip.module.travel_plan.domain.repository.day_schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ddd.caffeine.ratrip.module.travel_plan.domain.DaySchedulePlace;

@Repository
public interface DaySchedulePlaceRepository
	extends JpaRepository<DaySchedulePlace, Long>, DaySchedulePlaceQueryRepository {
}
