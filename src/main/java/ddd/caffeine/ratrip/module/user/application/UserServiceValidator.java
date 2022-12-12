package ddd.caffeine.ratrip.module.user.application;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import ddd.caffeine.ratrip.common.exception.CommonException;
import ddd.caffeine.ratrip.module.user.domain.User;

public class UserServiceValidator {
	public static void validateUserNotExist(User user) {
		if (isUserAlreadyExist(user)) {
			throw new CommonException(ALREADY_EXIST_USER_EXCEPTION);
		}
	}

	public static void validateUserExist(User user) {
		if (!isUserAlreadyExist(user)) {
			throw new CommonException(NOT_FOUND_USER_EXCEPTION);
		}
	}

	private static boolean isUserAlreadyExist(User user) {
		return user != null;
	}
}
