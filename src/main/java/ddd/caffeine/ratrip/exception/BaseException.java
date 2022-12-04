package ddd.caffeine.ratrip.exception;

import lombok.Getter;

/**
 * Runtime 에서 발생하는 모든 예외들을 받을 수 있는 부모 클래스.
 */
@Getter
public class BaseException extends RuntimeException {

	private ExceptionInformation information;

	/**
	 * BaseException 생성자.
	 */
	public BaseException() {
	}
}

