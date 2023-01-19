package ddd.caffeine.ratrip.module.travel_plan.domain.repository.implementation;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DayScheduleQueryRepositoryImpl {

	private final JPAQueryFactory jpaQueryFactory;

	// public DaySchedule findByUserAndTravelPlanUUID(UUID travelPlanUUID, int day) {
	// 	jpaQueryFactory.selectFrom(daySchedule)
	// 		.where(daySchedule.date.eq(handleLocalDate(
	// 			daySchedule.travelPlan.startDate, day - 1
	// 		)))
	// }
}
