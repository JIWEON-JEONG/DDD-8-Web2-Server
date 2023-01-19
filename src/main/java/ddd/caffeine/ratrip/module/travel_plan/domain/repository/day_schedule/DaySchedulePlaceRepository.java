package ddd.caffeine.ratrip.module.travel_plan.domain.repository.day_schedule;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ddd.caffeine.ratrip.module.travel_plan.domain.DaySchedule;

@Repository
public interface DaySchedulePlaceRepository extends JpaRepository<DaySchedule, UUID>,
	DaySchedulePlaceQueryRepository {

}
