package ddd.caffeine.ratrip.module.travel_plan.application.day_schedule;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import java.util.Optional;

import org.springframework.stereotype.Component;

import ddd.caffeine.ratrip.common.exception.domain.DaySchedulePlaceException;
import ddd.caffeine.ratrip.module.travel_plan.domain.day_schedule.DaySchedulePlace;

@Component
public class DaySchedulePlaceValidator {
	public DaySchedulePlace validateDaySchedulePlace(Optional<DaySchedulePlace> daySchedulePlace) {
		return daySchedulePlace.orElseThrow(
			() -> new DaySchedulePlaceException(NOT_FOUND_DAY_SCHEDULE_PLACE_EXCEPTION));
	}

	public DaySchedulePlace validateExistDaySchedulePlace(Optional<DaySchedulePlace> daySchedulePlace) {
		return daySchedulePlace.orElseThrow(
			() -> new DaySchedulePlaceException(NOT_FOUND_DAY_SCHEDULE_PLACE_EXCEPTION));
	}
}
