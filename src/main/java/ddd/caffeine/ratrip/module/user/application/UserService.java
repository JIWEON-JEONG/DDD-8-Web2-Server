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
		UserServiceUtils.validateExistUser(userRepository,
			SocialInfo.of(request.getSocialId(), request.getSocialType()));

		User user = userRepository.save(
			User.of(request.getName(), request.getEmail(), UserStatus.ACTIVE, request.getSocialId(),
				request.getSocialType()));

		return user.getId();
	}

	@Transactional
	public Long findUserBySocialIdAndSocialType(SignInUserDto request) {
		User user = UserServiceUtils.findUserBySocialIdAndSocialType(userRepository,
			SocialInfo.of(request.getSocialId(), request.getSocialType()));

		return user.getId();
	}
}
