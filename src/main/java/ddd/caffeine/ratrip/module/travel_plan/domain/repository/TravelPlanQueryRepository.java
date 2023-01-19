package ddd.caffeine.ratrip.module.travel_plan.domain.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import ddd.caffeine.ratrip.module.travel_plan.domain.repository.dao.LocalDateDao;

@Repository
public interface TravelPlanQueryRepository {
	LocalDateDao findLocalDateById(UUID id);
}
