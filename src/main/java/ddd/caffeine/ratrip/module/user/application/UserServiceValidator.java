package ddd.caffeine.ratrip.module.user.application;

import ddd.caffeine.ratrip.module.user.domain.User;

public class UserServiceValidator {
	public static void validateUserNotExist(User user) {
		if (isUserAlreadyExist(user)) {
			throw new RuntimeException("이미 존재하는 유저입니다.");
		}
	}

	public static void validateUserExist(User user) {
		if (!isUserAlreadyExist(user)) {
			throw new RuntimeException("존재하지 않는 유저입니다.");
		}
	}

	private static boolean isUserAlreadyExist(User user) {
		return user != null;
	}
}
