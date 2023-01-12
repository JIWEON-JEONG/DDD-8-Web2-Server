package ddd.caffeine.ratrip.common.exception;

import java.net.BindException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingRequestValueException;
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
	public ResponseEntity<ExceptionResponse> handleExpectedException(BaseException e) {
		log.error("code : {}, message : {}", e.getErrorCode(), e.getMessage());
		ExceptionResponse response = ExceptionResponse.builder()
			.httpStatus(e.getHttpStatus())
			.errorCode(e.getErrorCode())
			.message(e.getMessage())
			.build();

		return new ResponseEntity<>(response, response.getHttpStatus());
	}

	/**
	 * 예상하지 못한 예외들 처리.
	 * 즉 throw 하지 못한 예외들. Runtime 중에 발생하는 모든 예외들 처리.
	 *
	 * @param e RuntimeException
	 * @return ErrorResponse
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ExceptionResponse> handleUnexpectedException(RuntimeException e) {
		log.error("cause : {}, message : {}", e.getCause(), e.getMessage());
		ExceptionResponse response = ExceptionResponse.builder()
			.httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
			.errorCode("UNEXPECTED_EXCEPTION")
			.message(e.getMessage())
			.build();

		return new ResponseEntity<>(response, response.getHttpStatus());
	}

	/**
	 * 400 Bad Request
	 * Spring Validation 에서 발생하는 Exception.
	 *
	 * @param e BindException
	 * @return ErrorResponse
	 */
	@ExceptionHandler(BindException.class)
	public ResponseEntity<ExceptionResponse> handleBindException(BindException e) {
		log.error("cause : {}, message : {}", e.getCause(), e.getMessage());
		ExceptionResponse response = ExceptionResponse.builder()
			.httpStatus(HttpStatus.BAD_REQUEST)
			.errorCode("BIND_EXCEPTION")
			.message(e.getMessage())
			.build();

		return new ResponseEntity<>(response, response.getHttpStatus());
	}

	/**
	 * 400 Bad Request
	 * JSON 형식이 잘못되었을 때 발생하는 Exception. (Postman에서 테스트할 때 Json이 아닌 Text로 보내면 발생)
	 *
	 * @param e HttpMessageNotReadableException
	 * @return ErrorResponse
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		log.error("cause : {}, message : {}", e.getCause(), e.getMessage());
		ExceptionResponse response = ExceptionResponse.builder()
			.httpStatus(HttpStatus.BAD_REQUEST)
			.errorCode("HTTP_MESSAGE_NOT_READABLE_EXCEPTION")
			.message(e.getMessage())
			.build();

		return new ResponseEntity<>(response, response.getHttpStatus());
	}

	/**
	 * 400 BadRequest
	 * RequestParam, RequestPath, RequestPart 등의 필드가 입력되지 않은 경우 발생하는 Exception
	 *
	 * @param e MissingRequestValueException
	 * @return ErrorResponse
	 */
	@ExceptionHandler(MissingRequestValueException.class)
	public ResponseEntity<ExceptionResponse> handleMissingRequestValueException(MissingRequestValueException e) {
		log.error("cause : {}, message : {}", e.getCause(), e.getMessage());
		ExceptionResponse response = ExceptionResponse.builder()
			.httpStatus(HttpStatus.BAD_REQUEST)
			.errorCode("MISSING_REQUEST_VALUE_EXCEPTION")
			.message(e.getMessage())
			.build();

		return new ResponseEntity<>(response, response.getHttpStatus());
	}

	/**
	 * 400 BadRequest
	 * 파라미터 바인딩 시점에 타입이 맞지 않으면 내부적으로 발생하는 Exception
	 *
	 * @param e TypeMismatchException
	 * @return ErrorResponse
	 */
	@ExceptionHandler(TypeMismatchException.class)
	public ResponseEntity<ExceptionResponse> handleTypeMismatchException(TypeMismatchException e) {
		log.error("cause : {}, message : {}", e.getCause(), e.getMessage());
		ExceptionResponse response = ExceptionResponse.builder()
			.httpStatus(HttpStatus.BAD_REQUEST)
			.errorCode("TYPE_MISMATCH_EXCEPTION")
			.message(e.getMessage())
			.build();

		return new ResponseEntity<>(response, response.getHttpStatus());
	}
}

