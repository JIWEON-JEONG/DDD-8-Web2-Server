package ddd.caffeine.ratrip.module.auth.application.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.module.auth.application.AuthService;
import ddd.caffeine.ratrip.module.auth.application.TokenService;
import ddd.caffeine.ratrip.module.auth.application.dto.SignInDto;
import ddd.caffeine.ratrip.module.auth.application.dto.SignUpDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.SignInResponseDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.TokenResponseDto;
import ddd.caffeine.ratrip.module.external.apple.AppleTokenProvider;
import ddd.caffeine.ratrip.module.user.application.UserService;
import ddd.caffeine.ratrip.module.user.domain.UserSocialType;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AppleAuthService implements AuthService {
	private static final UserSocialType socialType = UserSocialType.APPLE;
	private final AppleTokenProvider appleTokenProvider;
	private final UserService userService;
	private final TokenService tokenService;

	@Override
	public SignInResponseDto signUp(SignUpDto request) {
		String socialId = getAppleProfileResponse(request.getToken());
		UUID userId = userService.registerUser(request.toRegisterUserDto(socialId, socialType));
		TokenResponseDto tokenResponseDto = tokenService.createTokenInfo(userId);

		return SignInResponseDto.of(userId, tokenResponseDto);
	}

	@Override
	public SignInResponseDto signIn(SignInDto request) {
		String socialId = getAppleProfileResponse(request.getToken());
		UUID userId = userService.findUserBySocialIdAndSocialType(socialId, socialType);
		TokenResponseDto tokenResponseDto = tokenService.createTokenInfo(userId);

		return SignInResponseDto.of(userId, tokenResponseDto);
	}

	private String getAppleProfileResponse(String token) {
		return appleTokenProvider.getSocialIdFromIdToken(token);
	}
}
