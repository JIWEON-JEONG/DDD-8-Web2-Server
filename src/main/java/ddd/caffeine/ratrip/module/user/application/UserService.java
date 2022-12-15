package ddd.caffeine.ratrip.module.user.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.module.user.application.dto.RegisterUserDto;
import ddd.caffeine.ratrip.module.user.application.dto.SignInUserDto;
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
	private final UserServiceValidator userServiceValidator;

	public UUID registerUser(RegisterUserDto request) {
		validateUserNotExist(SocialInfo.of(request.getSocialId(), request.getSocialType()));

		User user = userRepository.save(
			User.of(request.getName(), request.getEmail(), UserStatus.ACTIVE, request.getSocialId(),
				request.getSocialType()));

		return user.getId();
	}

	public UUID findUserBySocialIdAndSocialType(SignInUserDto request) {
		User user = findUserBySocialInfo(SocialInfo.of(request.getSocialId(), request.getSocialType()));
		return user.getId();
	}

	private void validateUserNotExist(SocialInfo socialInfo) {
		User user = userRepository.findUserBySocialInfo(socialInfo);
		userServiceValidator.validateUserNotExist(user);
	}

	private User findUserBySocialInfo(SocialInfo socialInfo) {
		User user = userRepository.findUserBySocialInfo(socialInfo);
		userServiceValidator.validateUserExist(user);

		return user;
	}
}
