package ddd.caffeine.ratrip.module.travel_plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ddd.caffeine.ratrip.module.travel_plan.model.TravelPlanUser;

public interface TravelPlanUserRepository extends JpaRepository<TravelPlanUser, Long> {
}
