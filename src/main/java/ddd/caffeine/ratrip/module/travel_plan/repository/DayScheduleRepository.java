package ddd.caffeine.ratrip.module.travel_plan.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ddd.caffeine.ratrip.module.travel_plan.model.DaySchedule;

public interface DayScheduleRepository extends JpaRepository<DaySchedule, UUID> {

}
