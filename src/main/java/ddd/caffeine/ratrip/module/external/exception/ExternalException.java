package ddd.caffeine.ratrip.module.external.exception;

import ddd.caffeine.ratrip.common.exception.BaseException;
import ddd.caffeine.ratrip.common.exception.ExceptionInformation;

public class ExternalException extends BaseException {
	public ExternalException(ExceptionInformation information) {
		super(information);
	}
}
