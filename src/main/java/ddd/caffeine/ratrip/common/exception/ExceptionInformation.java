package ddd.caffeine.ratrip.common.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * Error 에 관한 정보들이 저장되어 있는 클래스.
 */
@Getter
public enum ExceptionInformation {
	KAKAO_PAGE_NUMBER_EXCEPTION(BAD_REQUEST, "Page 는 1 이상 45 이하 여야 합니다."),
	;

	private HttpStatus httpStatus;
	private String message;

	ExceptionInformation(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}
}
