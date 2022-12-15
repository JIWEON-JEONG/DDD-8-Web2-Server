package ddd.caffeine.ratrip.module.user.application;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import org.springframework.stereotype.Component;

import ddd.caffeine.ratrip.common.exception.CommonException;
import ddd.caffeine.ratrip.module.user.domain.User;

@Component
public class UserServiceValidator {
	public void validateUserNotExist(User user) {
		if (isUserAlreadyExist(user)) {
			throw new CommonException(ALREADY_EXIST_USER_EXCEPTION);
		}
	}

	public void validateUserExist(User user) {
		if (!isUserAlreadyExist(user)) {
			throw new CommonException(NOT_FOUND_USER_EXCEPTION);
		}
	}

	private boolean isUserAlreadyExist(User user) {
		return user != null;
	}
}
