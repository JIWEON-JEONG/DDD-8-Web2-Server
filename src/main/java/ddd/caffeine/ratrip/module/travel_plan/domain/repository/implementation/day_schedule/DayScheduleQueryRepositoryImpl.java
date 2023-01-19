package ddd.caffeine.ratrip.module.travel_plan.domain.repository.implementation.day_schedule;

import static ddd.caffeine.ratrip.module.travel_plan.domain.QDaySchedule.*;

import java.time.LocalDate;
import java.util.UUID;

import com.querydsl.jpa.impl.JPAQueryFactory;

import ddd.caffeine.ratrip.module.travel_plan.domain.DaySchedule;
import ddd.caffeine.ratrip.module.travel_plan.domain.repository.day_schedule.DayScheduleQueryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DayScheduleQueryRepositoryImpl implements DayScheduleQueryRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public DaySchedule findByTravelPlanUUIDAndDate(UUID travelPlanUUID, LocalDate date) {
		return jpaQueryFactory.selectFrom(daySchedule)
			.where(
				daySchedule.travelPlan.id.eq(travelPlanUUID),
				daySchedule.date.eq(date)
			)
			.fetchOne();
	}
}
