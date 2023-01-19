package ddd.caffeine.ratrip.module.travel_plan.domain.repository.implementation;

import static ddd.caffeine.ratrip.module.travel_plan.domain.QTravelPlanUser.*;

import java.util.Optional;
import java.util.UUID;

import com.querydsl.jpa.impl.JPAQueryFactory;

import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlanUser;
import ddd.caffeine.ratrip.module.travel_plan.domain.repository.TravelPlanUserQueryRepository;
import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TravelPlanQueryRepositoryImpl {

	private final JPAQueryFactory jpaQueryFactory;
	
	Local
}
