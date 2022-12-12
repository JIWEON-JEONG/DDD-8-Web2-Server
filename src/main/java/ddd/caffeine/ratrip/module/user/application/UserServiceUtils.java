package ddd.caffeine.ratrip.module.user.application;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceUtils {

	// public static User findUserBySocialIdAndSocialType(UserRepository userRepository, SocialInfo socialInfo) {
	// 	return userRepository.findUserBySocialInfo(socialInfo).orElseThrow(
	// 		() -> {
	// 			throw new RuntimeException("NOT_FOUND_USER_EXCEPTION");
	// 		}
	// 	);
	// }
}
