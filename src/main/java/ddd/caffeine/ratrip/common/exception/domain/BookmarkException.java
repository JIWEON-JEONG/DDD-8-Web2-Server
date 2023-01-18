package ddd.caffeine.ratrip.common.exception.domain;

import ddd.caffeine.ratrip.common.exception.BaseException;
import ddd.caffeine.ratrip.common.exception.ExceptionInformation;

public class BookmarkException extends BaseException {
	public BookmarkException(int status, String errorCode, String message) {
		super(status, errorCode, message);
	}

	public BookmarkException(ExceptionInformation information) {
		super(information);
	}
}
