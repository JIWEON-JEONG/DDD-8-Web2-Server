package ddd.caffeine.ratrip.module.travel_plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ddd.caffeine.ratrip.module.travel_plan.model.TravelPlanUser;

@Repository
public interface TravelPlanUserRepository extends JpaRepository<TravelPlanUser, Long> {
}
