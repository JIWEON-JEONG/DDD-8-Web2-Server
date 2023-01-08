package ddd.caffeine.ratrip.module.user.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.module.user.application.dto.SignUpUserDto;
import ddd.caffeine.ratrip.module.user.domain.SocialInfo;
import ddd.caffeine.ratrip.module.user.domain.User;
import ddd.caffeine.ratrip.module.user.domain.UserStatus;
import ddd.caffeine.ratrip.module.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public UUID findUserIdBySocialIdAndSocialType(SignUpUserDto request) {
		User user = findUserBySocialInfo(SocialInfo.of(request.getSocialId(), request.getSocialType()));

		if (userNotExist(user)) {
			return registerUserAndGetUserId(request);
		}

		return user.getId();
	}

	private User findUserBySocialInfo(SocialInfo socialInfo) {
		return userRepository.findUserBySocialInfo(socialInfo);
	}

	private boolean userNotExist(User user) {
		return user == null;
	}

	public UUID registerUserAndGetUserId(SignUpUserDto request) {
		User user = userRepository.save(
			User.of(request.getName(), request.getEmail(), UserStatus.ACTIVE, request.getSocialId(),
				request.getSocialType()));

		return user.getId();
	}
}
