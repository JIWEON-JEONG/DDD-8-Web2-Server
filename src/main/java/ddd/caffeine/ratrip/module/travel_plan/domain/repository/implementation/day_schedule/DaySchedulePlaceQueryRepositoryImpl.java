package ddd.caffeine.ratrip.module.travel_plan.domain.repository.implementation;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DaySchedulePlaceQueryRepositoryImpl {

	private final JPAQueryFactory jpaQueryFactory;

	// public List<DaySchedulePlaceDao> findByUserAndTravelPlanUUID(UUID dayScheduleUUID, ) {
	// 	jpaQueryFactory
	// 		.select(new QDaySchedulePlaceDao(place.id, place.name, place.category,
	// 			daySchedulePlace.memo, daySchedulePlace.order))
	// 		.from(daySchedulePlace)
	// 		.join(daySchedulePlace.place, place)
	// 		.where(
	// 			daySchedulePlace.daySchedule.id.eq(dayScheduleUUID)
	// 		)
	// 		.orderBy(daySchedulePlace.order.asc())
	// 		.offset(pageable.getOffset())
	// 		.limit(pageable.getPageSize() + 1)
	// 		.fetch();
	//
	// 	return QuerydslUtils.toSlice(contents, pageable);
	// }

}
