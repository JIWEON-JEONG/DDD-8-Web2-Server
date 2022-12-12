package ddd.caffeine.ratrip.module.user.application;

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
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	@Transactional
	public Long registerUser(RegisterUserDto request) {
		validateUserNotExist(SocialInfo.of(request.getSocialId(), request.getSocialType()));

		User user = userRepository.save(
			User.of(request.getName(), request.getEmail(), UserStatus.ACTIVE, request.getSocialId(),
				request.getSocialType()));

		return user.getId();
	}

	@Transactional
	public Long findUserBySocialIdAndSocialType(SignInUserDto request) {
		User user = userRepository.findUserBySocialInfo(SocialInfo.of(request.getSocialId(), request.getSocialType()));
		UserServiceValidator.validateUserExist(user);

		return user.getId();
	}

	private void validateUserNotExist(SocialInfo socialInfo) {
		User user = userRepository.findUserBySocialInfo(socialInfo);
		UserServiceValidator.validateUserNotExist(user);
	}
}
