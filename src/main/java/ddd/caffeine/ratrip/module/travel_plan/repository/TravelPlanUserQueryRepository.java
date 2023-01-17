package ddd.caffeine.ratrip.module.travel_plan.repository;

import java.util.UUID;

import ddd.caffeine.ratrip.module.user.domain.User;

public interface TravelPlanUserQueryRepository {
	boolean existByUserAndTravelPlanUUID(User user, UUID travelPlanUUID);
}
