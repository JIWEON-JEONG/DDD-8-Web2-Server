package ddd.caffeine.ratrip.module.travel_plan.domain.repository.implementation;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DaySchedulePlaceQueryRepositoryImpl {

	private final JPAQueryFactory jpaQueryFactory;

	// public List<DaySchedulePlace> findByUserAndTravelPlanUUID(UUID travelPlanUUID, int order) {
	// 	TravelPlanUser response = jpaQueryFactory
	// 		.selectFrom(daySchedulePlace)
	// 		.where(
	// 			daySchedulePlace.daySchedule.travelPlan.eq()
	// 		)
	// }

}
