package ddd.caffeine.ratrip.common.exception.domain;

import ddd.caffeine.ratrip.common.exception.BaseException;
import ddd.caffeine.ratrip.common.exception.ExceptionInformation;
import lombok.Getter;

@Getter
public class PlaceException extends BaseException {

	public PlaceException(int status, String errorCode, String message) {
		super(status, errorCode, message);
	}

	public PlaceException(ExceptionInformation information) {
		super(information);
	}
}
