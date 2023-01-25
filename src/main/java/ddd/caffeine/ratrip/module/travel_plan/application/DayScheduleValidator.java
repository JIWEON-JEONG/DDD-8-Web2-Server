package ddd.caffeine.ratrip.module.travel_plan.application;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import java.util.Optional;

import org.springframework.stereotype.Component;

import ddd.caffeine.ratrip.common.exception.domain.DayScheduleException;
import ddd.caffeine.ratrip.module.travel_plan.domain.DaySchedule;

@Component
public class DayScheduleValidator {
	public DaySchedule validateExistDaySchedule(Optional<DaySchedule> daySchedule) {
		return daySchedule.orElseThrow(() -> new DayScheduleException(NOT_FOUND_DAY_SCHEDULE_EXCEPTION));
	}
}
