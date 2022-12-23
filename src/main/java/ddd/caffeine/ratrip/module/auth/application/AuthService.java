package ddd.caffeine.ratrip.module.auth.application;

import ddd.caffeine.ratrip.module.auth.application.dto.SignInDto;
import ddd.caffeine.ratrip.module.auth.application.dto.SignUpDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.SignInResponseDto;

public interface AuthService {
	SignInResponseDto signUp(SignUpDto request);

	SignInResponseDto signIn(SignInDto request);
}
