package ddd.caffeine.ratrip.module.travel_plan.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlan;

@Repository
public interface TravelPlanRepository extends JpaRepository<TravelPlan, UUID> {
}
