package ddd.caffeine.ratrip.module.travel_plan.domain;

import java.util.UUID;

import ddd.caffeine.ratrip.module.user.domain.User;

public class TravelPlanAccessOption {
	private User user;
	private UUID travelPlanUUID;

	public TravelPlanAccessOption(User user, String travelPlanUUID) {
		this.user = user;
		this.travelPlanUUID = UUID.fromString(travelPlanUUID);
	}

	public TravelPlanAccessOption(User user, UUID travelPlanUUID) {
		this.user = user;
		this.travelPlanUUID = travelPlanUUID;
	}

	public User readUser() {
		return this.user;
	}

	public UUID readTravelPlanUUID() {
		return this.travelPlanUUID;
	}

}
