package ddd.caffeine.ratrip.common.exception.domain;

import ddd.caffeine.ratrip.common.exception.BaseException;
import ddd.caffeine.ratrip.common.exception.ExceptionInformation;
import lombok.Getter;

@Getter
public class DayScheduleException extends BaseException {

	public DayScheduleException(int status, String errorCode, String message) {
		super(status, errorCode, message);
	}

	public DayScheduleException(ExceptionInformation information) {
		super(information);
	}
}
