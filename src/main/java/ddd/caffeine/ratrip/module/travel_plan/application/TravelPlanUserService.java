package ddd.caffeine.ratrip.module.travel_plan.application;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import ddd.caffeine.ratrip.common.exception.domain.TravelPlanException;
import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlan;
import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlanAccessOption;
import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlanUser;
import ddd.caffeine.ratrip.module.travel_plan.domain.repository.TravelPlanUserRepository;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanOngoingResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanResponseDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanResponseModel;
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

	public TravelPlanResponseDto readByUser(User user, Pageable pageable) {
		List<TravelPlanResponseModel> contents = new ArrayList<>();
		Slice<TravelPlanUser> travelPlanUser = travelPlanUserRepository.findByUser(user, pageable);
		for (TravelPlanUser entity : travelPlanUser.getContent()) {
			contents.add(new TravelPlanResponseModel(entity.getTravelPlan()));
		}
		return TravelPlanResponseDto.builder()
			.contents(contents)
			.hasNext(travelPlanUser.hasNext())
			.build();
	}

	public TravelPlanOngoingResponseDto readByUserUnfinishedTravel(User user) {
		TravelPlanUser travelPlanUser = travelPlanUserRepository.findByUserUnfinishedTravel(user);
		//작성중인 여행 없을 경우,
		if (travelPlanUser == null) {
			return new TravelPlanOngoingResponseDto(Boolean.FALSE);
		}
		//작성중인 여행이 있을 경우,
		return TravelPlanOngoingResponseDto.builder()
			.content(new TravelPlanResponseModel(travelPlanUser.readTravelPlan()))
			.hasPlan(Boolean.TRUE)
			.build();
	}

	public void validateAccessTravelPlan(TravelPlanAccessOption accessOption) {
		if (travelPlanUserRepository.existByUserAndTravelPlanUUID(accessOption.readUser(),
			accessOption.readTravelPlanUUID())) {
			return;
		}
		throw new TravelPlanException(UNAUTHORIZED_ACCESS_TRAVEL_PLAN);
	}
}
