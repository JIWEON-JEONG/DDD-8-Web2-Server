package ddd.caffeine.ratrip.exception.domain;

import ddd.caffeine.ratrip.exception.BaseException;
import lombok.Getter;

@Getter
public class KakaoFeignException extends BaseException {

	public KakaoFeignException(int status, String errorCode, String message) {
		super(status, errorCode, message);
	}
}
