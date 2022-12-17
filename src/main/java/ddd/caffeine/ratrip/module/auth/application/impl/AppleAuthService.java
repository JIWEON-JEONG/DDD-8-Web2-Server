package ddd.caffeine.ratrip.module.auth.application.impl;

import org.springframework.stereotype.Service;

import ddd.caffeine.ratrip.module.auth.application.AuthService;
import ddd.caffeine.ratrip.module.auth.application.dto.SignInDto;
import ddd.caffeine.ratrip.module.auth.application.dto.SignUpDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.SignInResponseDto;
import ddd.caffeine.ratrip.module.external.apple.AppleTokenProvider;
import ddd.caffeine.ratrip.module.user.domain.UserSocialType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppleAuthService implements AuthService {
	private static final UserSocialType socialType = UserSocialType.APPLE;
	private final AppleTokenProvider appleTokenProvider;

	@Override
	public SignInResponseDto signUp(SignUpDto request) {
		return null;
	}

	@Override
	public SignInResponseDto signIn(SignInDto request) {
		return null;
	}
}
