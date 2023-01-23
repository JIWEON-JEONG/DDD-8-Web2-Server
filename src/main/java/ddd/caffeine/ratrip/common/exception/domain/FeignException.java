package ddd.caffeine.ratrip.common.exception.domain;

import ddd.caffeine.ratrip.common.exception.BaseException;
import ddd.caffeine.ratrip.common.exception.ExceptionInformation;
import lombok.Getter;

@Getter
public class FeignException extends BaseException {

	public FeignException(int status, String errorCode, String message) {
		super(status, errorCode, message);
	}

	public FeignException(ExceptionInformation information) {
		super(information);
	}
}
