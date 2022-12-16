package ddd.caffeine.ratrip.module.auth.application.impl;

import ddd.caffeine.ratrip.module.auth.application.AuthService;
import ddd.caffeine.ratrip.module.auth.application.dto.SignInDto;
import ddd.caffeine.ratrip.module.auth.application.dto.SignUpDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.SignInResponseDto;

public class AppleAuthService implements AuthService {
	@Override
	public SignInResponseDto signUp(SignUpDto request) {
		return null;
	}

	@Override
	public SignInResponseDto signIn(SignInDto request) {
		return null;
	}
}
