package ddd.caffeine.ratrip.module.travel_plan.application;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.common.exception.domain.TravelPlanException;
import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlan;
import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlanUser;
import ddd.caffeine.ratrip.module.travel_plan.domain.repository.TravelPlanUserRepository;
import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelPlanUserService {

	private final TravelPlanUserRepository travelPlanUserRepository;

	@Transactional
	public void saveTravelPlanWithUser(TravelPlan travelPlan, User user) {
		TravelPlanUser travelPlanUser = new TravelPlanUser(travelPlan, user);
		travelPlanUserRepository.save(travelPlanUser);
	}

	@Transactional(readOnly = true)
	public Optional<TravelPlanUser> readByUserUnfinishedTravel(User user) {
		return travelPlanUserRepository.findByUserUnfinishedTravel(user);
	}

	@Transactional(readOnly = true)
	public void validateAccessTravelPlan(User user, String travelPlanUUID) {
		if (travelPlanUserRepository.existByUserAndTravelPlanUUID(user, UUID.fromString(travelPlanUUID))) {
			return;
		}
		throw new TravelPlanException(UNAUTHORIZED_ACCESS_TRAVEL_PLAN);
	}
}
