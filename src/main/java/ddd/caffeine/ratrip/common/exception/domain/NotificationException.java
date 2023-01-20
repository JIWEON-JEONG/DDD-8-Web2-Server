package ddd.caffeine.ratrip.common.exception.domain;

import ddd.caffeine.ratrip.common.exception.BaseException;
import ddd.caffeine.ratrip.common.exception.ExceptionInformation;

public class NotificationException extends BaseException {

	public NotificationException(int status, String errorCode, String message) {
		super(status, errorCode, message);
	}

	public NotificationException(ExceptionInformation information) {
		super(information);
	}
}
