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
public class TravelPlanUserQueryRepositoryImpl implements TravelPlanUserQueryRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public boolean existByUserAndTravelPlanUUID(User user, UUID travelPlanUUID) {
		return jpaQueryFactory
			.select(travelPlanUser.id)
			.from(travelPlanUser)
			.where(
				travelPlanUser.user.eq(user),
				travelPlanUser.travelPlan.id.eq(travelPlanUUID)
			)
			.fetchFirst() != null;
	}

	public Optional<TravelPlanUser> findByUserUnfinishedTravel(User user) {
		TravelPlanUser response = jpaQueryFactory
			.selectFrom(travelPlanUser)
			.where(
				travelPlanUser.user.eq(user),
				travelPlanUser.travelPlan.isEnd.isFalse()
			)
			.fetchOne();

		return Optional.of(response);
	}

}
