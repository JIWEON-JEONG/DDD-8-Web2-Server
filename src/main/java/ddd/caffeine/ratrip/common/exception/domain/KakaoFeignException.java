package ddd.caffeine.ratrip.common.exception.domain;

import ddd.caffeine.ratrip.common.exception.BaseException;
import ddd.caffeine.ratrip.common.exception.ExceptionInformation;
import lombok.Getter;

@Getter
public class KakaoFeignException extends BaseException {

	public KakaoFeignException(int status, String errorCode, String message) {
		super(status, errorCode, message);
	}

	public KakaoFeignException(ExceptionInformation information) {
		super(information);
	}
}
