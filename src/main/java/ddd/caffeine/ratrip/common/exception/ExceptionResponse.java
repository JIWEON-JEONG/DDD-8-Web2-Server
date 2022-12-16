package ddd.caffeine.ratrip.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 실제 client 에게 response 될 클래스.
 * Json 객체로 return 됨. (RestControllerAdvice)
 *
 * @JsonInclude(JsonInclude.Include.NON_EMPTY) : null 값일 경우 json 으로 리턴할때 제외 시킨다.
 */
@Getter
@Builder
@ToString
public class ExceptionResponse {

	private HttpStatus httpStatus;

	/**
	 * 프론트 개발자들과 상의 후 넣을지 안넣을지 결정.
	 */
	private String errorCode;

	private String message;

	public static ExceptionResponse of(HttpStatus httpStatus, String errorCode, String message) {
		return ExceptionResponse.builder()
			.httpStatus(httpStatus)
			.errorCode(errorCode)
			.message(message)
			.build();
	}
}
