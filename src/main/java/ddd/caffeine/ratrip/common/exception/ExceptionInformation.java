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
	JSON_TO_OBJECT_MAPPING_EXCEPTION(BAD_REQUEST, "JSON 을 객체로 변환 하지 못하였습니다."),
	INVALID_BEARER_FORMAT_EXCEPTION(BAD_REQUEST, "Bearer 토큰의 형식이 올바르지 않습니다."),

	// 401 Unauthorized
	DIFFERENT_REFRESH_TOKEN_EXCEPTION(UNAUTHORIZED, "DB의 리프레시 토큰과 일치하지 않습니다."),
	INVALID_JWT_SIGNATURE_EXCEPTION(UNAUTHORIZED, "잘못된 JWT 서명입니다."),
	INVALID_JWT_TOKEN_EXCEPTION(UNAUTHORIZED, "잘못된 JWT 토큰입니다."),
	EXPIRED_JWT_TOKEN_EXCEPTION(UNAUTHORIZED, "만료된 JWT 토큰입니다."),
	UNSUPPORTED_JWT_TOKEN_EXCEPTION(UNAUTHORIZED, "지원되지 않는 JWT 토큰입니다."),

	// 403 Forbidden

	// 404 Not Found
	NOT_FOUND_USER_EXCEPTION(NOT_FOUND, "존재하지 않는 유저입니다."),
	EMPTY_HEADER_EXCEPTION(NOT_FOUND, "헤더가 비어있습니다."),
	NOT_FOUND_REFRESH_TOKEN_EXCEPTION(NOT_FOUND, "DB에 존재하지 않는 리프레시 토큰입니다."),
	NOT_FOUND_JWT_CLAIMS_EXCEPTION(NOT_FOUND, "JWT 토큰에 클레임이 존재하지 않습니다."),
	NOT_FOUND_JWT_USERID_EXCEPTION(NOT_FOUND, "JWT 토큰에 userId가 비어 있습니다."),
	NOT_FOUND_PUBLIC_KEY_EXCEPTION(NOT_FOUND, "일치하는 Public Key가 존재하지 않습니다"),

	// 409 Conflict
	ALREADY_EXIST_USER_EXCEPTION(CONFLICT, "이미 존재하는 유저입니다."),

	// 500 Internal Server Error
	;

	private final HttpStatus httpStatus;
	private final String message;

	ExceptionInformation(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}
}
