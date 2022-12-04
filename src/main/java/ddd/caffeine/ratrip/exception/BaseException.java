package ddd.caffeine.ratrip.exception;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

/**
 * Runtime 에서 발생하는 모든 예외들을 받을 수 있는 부모 클래스.
 */
@Getter
public class BaseException extends RuntimeException {

	private HttpStatus httpStatus;

	/**
	 * 프론트 개발자들과 상의 후 넣을지 안넣을지 결정.
	 */
	private String errorCode;

	private String message;

	/**
	 * ExceptionInformation Enum Type 활용 생성자
	 */
	public BaseException(ExceptionInformation information) {
		this.httpStatus = information.getHttpStatus();
		this.errorCode = information.toString();
		this.message = information.getMessage();
	}

	/**
	 * field 주입 생성자
	 * - 외부 API 통신에 활용.
	 */
	@Builder
	public BaseException(int status, String errorCode, String message) {
		this.httpStatus = HttpStatus.valueOf(status);
		this.errorCode = errorCode;
		this.message = message;
	}
}

