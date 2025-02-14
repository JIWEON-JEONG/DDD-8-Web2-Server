package ddd.caffeine.ratrip.module.travel_plan.application.day_schedule;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import java.util.Optional;

import org.springframework.stereotype.Component;

import ddd.caffeine.ratrip.common.exception.domain.DayScheduleException;
import ddd.caffeine.ratrip.module.travel_plan.domain.day_schedule.DaySchedule;

@Component
public class DayScheduleValidator {
	public DaySchedule validateExistOptionalDaySchedule(Optional<DaySchedule> daySchedule) {
		return daySchedule.orElseThrow(() -> new DayScheduleException(NOT_FOUND_DAY_SCHEDULE_EXCEPTION));
	}

	public void validateExistDaySchedule(DaySchedule daySchedule) {
		if (daySchedule == null) {
			throw new DayScheduleException(NOT_FOUND_DAY_SCHEDULE_EXCEPTION);
		}
	}
}
