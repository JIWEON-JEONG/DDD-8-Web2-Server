package ddd.caffeine.ratrip.module.travel_plan.domain.repository;

import java.util.UUID;

import ddd.caffeine.ratrip.module.travel_plan.domain.repository.dao.LocalDateDao;

public interface TravelPlanQueryRepository {
	LocalDateDao findLocalDateById(UUID id);

	void test();
}
