package ddd.caffeine.ratrip.module.travel_plan.domain.repository;

import java.util.UUID;

import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlanUser;
import ddd.caffeine.ratrip.module.user.domain.User;

public interface TravelPlanUserQueryRepository {
	boolean existByUserAndTravelPlanUUID(User user, UUID travelPlanUUID);

	TravelPlanUser findByUserUnfinishedTravel(User user);
}
