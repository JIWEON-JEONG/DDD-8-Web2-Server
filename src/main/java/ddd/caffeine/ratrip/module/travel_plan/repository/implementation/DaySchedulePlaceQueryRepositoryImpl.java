package ddd.caffeine.ratrip.module.travel_plan.repository.implementation;

import static ddd.caffeine.ratrip.module.travel_plan.model.QDaySchedulePlace.*;
import static ddd.caffeine.ratrip.module.travel_plan.model.QTravelPlanUser.*;
import static ddd.caffeine.ratrip.module.user.domain.QUser.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.querydsl.jpa.impl.JPAQueryFactory;

import ddd.caffeine.ratrip.module.travel_plan.model.DaySchedulePlace;
import ddd.caffeine.ratrip.module.travel_plan.model.QDaySchedulePlace;
import ddd.caffeine.ratrip.module.travel_plan.model.TravelPlanUser;
import ddd.caffeine.ratrip.module.travel_plan.repository.TravelPlanUserQueryRepository;
import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DaySchedulePlaceQueryRepositoryImpl {

	private final JPAQueryFactory jpaQueryFactory;

	public List<DaySchedulePlace> findByUserAndTravelPlanUUID(UUID travelPlanUUID, int order) {
		TravelPlanUser response = jpaQueryFactory
			.selectFrom(daySchedulePlace)
			.where(
				daySchedulePlace.daySchedule.travelPlan.eq()
			)
	}

}
