package ddd.caffeine.ratrip.module.travel_plan.domain.repository.implementation;

import static ddd.caffeine.ratrip.module.travel_plan.domain.QTravelPlan.*;

import java.util.UUID;

import com.querydsl.jpa.impl.JPAQueryFactory;

import ddd.caffeine.ratrip.module.travel_plan.domain.repository.TravelPlanQueryRepository;
import ddd.caffeine.ratrip.module.travel_plan.domain.repository.dao.LocalDateDao;
import ddd.caffeine.ratrip.module.travel_plan.domain.repository.dao.QLocalDateDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TravelPlanQueryRepositoryImpl implements TravelPlanQueryRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public LocalDateDao findLocalDateById(UUID id) {
		return jpaQueryFactory
			.select(new QLocalDateDao(travelPlan.startDate))
			.from(travelPlan)
			.where(
				travelPlan.id.eq(id)
			)
			.fetchOne();
	}

}
