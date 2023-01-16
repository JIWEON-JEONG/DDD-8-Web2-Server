package ddd.caffeine.ratrip.common.exception.domain;

import ddd.caffeine.ratrip.common.exception.BaseException;
import ddd.caffeine.ratrip.common.exception.ExceptionInformation;

public class UserException extends BaseException {
	public UserException(int status, String errorCode, String message) {
		super(status, errorCode, message);
	}

	public UserException(ExceptionInformation information) {
		super(information);
	}
}
