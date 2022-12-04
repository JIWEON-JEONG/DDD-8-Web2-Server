package ddd.caffeine.ratrip.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

/**
 * 실제 client 에게 response 될 클래스.
 * Json 객체로 return 됨. (RestControllerAdvice)
 *
 * @JsonInclude(JsonInclude.Include.NON_EMPTY) : null 값일 경우 json 으로 리턴할때 제외 시킨다.
 */
@Getter
@Builder
public class ExceptionResponse {

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private HttpStatus httpStatus;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String errorCode;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String message;
}
