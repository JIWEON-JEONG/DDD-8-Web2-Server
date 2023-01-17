package ddd.caffeine.ratrip.module.travel_plan.repository.implementation;

import static ddd.caffeine.ratrip.module.travel_plan.model.QTravelPlanUser.*;

import java.util.UUID;

import com.querydsl.jpa.impl.JPAQueryFactory;

import ddd.caffeine.ratrip.module.travel_plan.repository.TravelPlanUserQueryRepository;
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

}
