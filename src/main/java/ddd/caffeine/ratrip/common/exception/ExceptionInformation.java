package ddd.caffeine.ratrip.common.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * Error 에 관한 정보들이 저장되어 있는 클래스.
 */
@Getter
public enum ExceptionInformation {
	// 400 Bad Request
	ILLEGAL_YML_PROPERTIES_EXCEPTION(BAD_REQUEST, "YML 파일을 읽어올 수 없습니다."),
	KAKAO_PAGE_NUMBER_EXCEPTION(BAD_REQUEST, "Page 는 1 이상 45 이하 여야 합니다."),
	;

	// 401 Unauthorized

	// 403 Forbidden

	// 404 Not Found

	// 500 Internal Server Error

	private final HttpStatus httpStatus;
	private final String message;

	ExceptionInformation(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}
}
