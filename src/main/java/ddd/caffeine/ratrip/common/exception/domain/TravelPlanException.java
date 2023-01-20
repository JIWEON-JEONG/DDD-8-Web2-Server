package ddd.caffeine.ratrip.common.exception.domain;

import ddd.caffeine.ratrip.common.exception.BaseException;
import ddd.caffeine.ratrip.common.exception.ExceptionInformation;
import lombok.Getter;

@Getter
public class TravelPlanException extends BaseException {

	public TravelPlanException(int status, String errorCode, String message) {
		super(status, errorCode, message);
	}

	public TravelPlanException(ExceptionInformation information) {
		super(information);
	}
}
