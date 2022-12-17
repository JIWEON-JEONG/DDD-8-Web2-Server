package ddd.caffeine.ratrip.common.exception.domain;

import ddd.caffeine.ratrip.common.exception.BaseException;
import ddd.caffeine.ratrip.common.exception.ExceptionInformation;

public class CommonException extends BaseException {
	public CommonException(ExceptionInformation information) {
		super(information);
	}
}
