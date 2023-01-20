package ddd.caffeine.ratrip.module.travel_plan.application;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.common.exception.domain.TravelPlanException;
import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlan;
import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlanAccessOption;
import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlanUser;
import ddd.caffeine.ratrip.module.travel_plan.domain.repository.TravelPlanUserRepository;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanResponseDto;
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
	public TravelPlanResponseDto readByUserUnfinishedTravel(User user) {
		TravelPlanUser travelPlanUser = travelPlanUserRepository.findByUserUnfinishedTravel(user);
		//작성중인 여행 없을 경우,
		if (travelPlanUser == null) {
			return new TravelPlanResponseDto(Boolean.FALSE);
		}
		//작성중인 여행이 있을 경우,
		return TravelPlanResponseDto.builder()
			.travelPlan(travelPlanUser.readTravelPlan())
			.hasPlan(Boolean.TRUE)
			.build();
	}

	@Transactional(readOnly = true)
	public void validateAccessTravelPlan(TravelPlanAccessOption accessOption) {
		if (travelPlanUserRepository.existByUserAndTravelPlanUUID(accessOption.readUser(),
			accessOption.readTravelPlanUUID())) {
			return;
		}
		throw new TravelPlanException(UNAUTHORIZED_ACCESS_TRAVEL_PLAN);
	}
}
