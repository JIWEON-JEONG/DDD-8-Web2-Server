package ddd.caffeine.ratrip.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * RestControllerAdvice : 모든 @Controller 에 대한 전역적으로 발생 할 수 있는 예외를 잡아서 처리 가능.
 * RestControllerAdvice 는 응답을 JSON 객체로 리턴 할 수 있기 때문에 선택. (ErrorCode 객체 리턴)
 * ExceptionHandler : 어노테이션을 메서드에 선언하고 특정 예외 클래스를 지정해주면 해당 예외가 발생했을 때 메서드에 정의한 로직을 처리.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * BaseException 으로 throw 한 예외들 처리 하는 메서드.
	 *
	 * @param e BaseException
	 * @return ErrorResponse
	 */
	@ExceptionHandler(BaseException.class)
	public ExceptionResponse handleExpectedException(BaseException e) {
		log.error("BaseException", e);
		return ExceptionResponse.builder()
			.httpStatus(e.getHttpStatus())
			.errorCode(e.getErrorCode())
			.message(e.getMessage())
			.build();
	}

	/**
	 * 예상하지 못한 예외들 처리.
	 * 즉 throw 하지 못한 예외들. Runtime 중에 발생하는 모든 예외들 처리.
	 *
	 * @param e RuntimeException
	 * @return ErrorResponse
	 */
	@ExceptionHandler(RuntimeException.class)
	public ExceptionResponse handleUnexpectedException(RuntimeException e) {
		log.error("RuntimeException", e);
		return ExceptionResponse.builder()
			.httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
			.errorCode("UNEXPECTED_EXCEPTION")
			.message(e.getMessage())
			.build();
	}
}

