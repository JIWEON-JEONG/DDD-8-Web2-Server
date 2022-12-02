package ddd.caffeine.ratrip.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * Error 에 관한 정보들이 저장되어 있는 클래스.
 *
 * ErrorCode : 클라이언트가 에러 코드를 보고 처리 할 수 있도록 하기 위해 존재.
 */
@Getter
public enum Error {
	;

	private String errorCode;
	private HttpStatus httpStatus;
	private String message;

	Error(String errorCode, HttpStatus httpStatus, String message) {
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
		this.message = message;
	}
}
