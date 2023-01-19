package ddd.caffeine.ratrip.module.travel_plan.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlanUser;

@Repository
public interface TravelPlanUserRepository extends JpaRepository<TravelPlanUser, Long> {
}
