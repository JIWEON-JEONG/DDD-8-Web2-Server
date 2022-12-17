package ddd.caffeine.ratrip.common.exception.domain;

import ddd.caffeine.ratrip.common.exception.BaseException;
import ddd.caffeine.ratrip.common.exception.ExceptionInformation;

public class CoreException extends BaseException {

	public CoreException(ExceptionInformation exceptionInformation) {
		super(exceptionInformation);
	}

	public CoreException(int status, String errorCode, String message) {
		super(status, errorCode, message);
	}
}
