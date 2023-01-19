package ddd.caffeine.ratrip.module.travel_plan.domain.repository.implementation.day_schedule;

import static ddd.caffeine.ratrip.module.place.domain.QPlace.*;
import static ddd.caffeine.ratrip.module.travel_plan.domain.QDaySchedulePlace.*;

import java.util.List;
import java.util.UUID;

import com.querydsl.jpa.impl.JPAQueryFactory;

import ddd.caffeine.ratrip.module.travel_plan.domain.repository.dao.DaySchedulePlaceDao;
import ddd.caffeine.ratrip.module.travel_plan.domain.repository.dao.QDaySchedulePlaceDao;
import ddd.caffeine.ratrip.module.travel_plan.domain.repository.day_schedule.DaySchedulePlaceRepositoryCustom;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DaySchedulePlaceRepositoryCustomImpl implements DaySchedulePlaceRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<DaySchedulePlaceDao> loadDaySchedulePlaces(UUID dayScheduleId) {
		return jpaQueryFactory
			.select(new QDaySchedulePlaceDao(place.id, place.name, place.category,
				daySchedulePlace.memo, daySchedulePlace.order))
			.from(daySchedulePlace)
			.join(daySchedulePlace.place, place)
			.where(
				daySchedulePlace.daySchedule.id.eq(dayScheduleId)
			)
			.orderBy(daySchedulePlace.order.asc())
			.fetch();
	}

}
