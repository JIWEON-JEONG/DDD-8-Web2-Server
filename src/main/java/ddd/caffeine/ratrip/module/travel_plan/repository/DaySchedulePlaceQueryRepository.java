package ddd.caffeine.ratrip.module.travel_plan.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import ddd.caffeine.ratrip.module.travel_plan.model.DaySchedulePlace;

@Repository
public interface DaySchedulePlaceQueryRepository {
	List<DaySchedulePlace> findDaySchedulePlacesBy
}
