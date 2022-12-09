package ddd.caffeine.ratrip.module.user.application;

import ddd.caffeine.ratrip.module.user.domain.SocialInfo;
import ddd.caffeine.ratrip.module.user.domain.User;
import ddd.caffeine.ratrip.module.user.domain.repository.UserRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceUtils {
	public static void validateExistUser(UserRepository userRepository, SocialInfo socialInfo) {
		userRepository.findUserBySocialInfo(socialInfo).ifPresent(
			user -> {
				throw new RuntimeException("이미 존재하는 유저입니다."); //TODO - 추후 수정 필요
			}
		);
	}

	public static User findUserBySocialIdAndSocialType(UserRepository userRepository, SocialInfo socialInfo) {
		return userRepository.findUserBySocialInfo(socialInfo).orElseThrow(
			() -> {
				throw new RuntimeException("NOT_FOUND_USER_EXCEPTION");
			}
		);
	}
}
