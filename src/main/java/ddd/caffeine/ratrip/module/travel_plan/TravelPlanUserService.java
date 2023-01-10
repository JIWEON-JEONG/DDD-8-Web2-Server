package ddd.caffeine.ratrip.module.travel_plan;

import org.springframework.stereotype.Service;

import ddd.caffeine.ratrip.module.travel_plan.model.TravelPlan;
import ddd.caffeine.ratrip.module.travel_plan.model.TravelPlanUser;
import ddd.caffeine.ratrip.module.travel_plan.repository.TravelPlanUserRepository;
import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelPlanUserService {

	private final TravelPlanUserRepository travelPlanUserRepository;

	public void saveTravelPlanWithUser(TravelPlan travelPlan, User user) {
		TravelPlanUser travelPlanUser = new TravelPlanUser(travelPlan, user);
		travelPlanUserRepository.save(travelPlanUser);
	}
}
