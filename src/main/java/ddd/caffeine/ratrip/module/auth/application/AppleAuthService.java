package ddd.caffeine.ratrip.module.auth.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.module.auth.application.dto.SignUpWithAppleDto;
import ddd.caffeine.ratrip.module.auth.external.apple.AppleTokenProvider;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.SignInResponseDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.TokenResponseDto;
import ddd.caffeine.ratrip.module.user.application.UserService;
import ddd.caffeine.ratrip.module.user.domain.UserSocialType;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AppleAuthService {
	private static final UserSocialType socialType = UserSocialType.APPLE;
	private final AppleTokenProvider appleTokenProvider;
	private final UserService userService;
	private final TokenService tokenService;

	public SignInResponseDto signUpWithApple(SignUpWithAppleDto request) {
		String socialId = getAppleProfileResponse(request.getToken());
		UUID userId = userService.registerUser(request.registerUserUsedByAppleAuth(socialId, socialType));
		TokenResponseDto tokenResponseDto = tokenService.createTokenInfo(userId);

		return SignInResponseDto.of(userId, tokenResponseDto);
	}

	public SignInResponseDto signInWithApple(SignInWithKakaoDto request) {
		String socialId = getAppleProfileResponse(request.getToken());
		UUID userId = userService.findUserBySocialIdAndSocialType(socialId, socialType);
		TokenResponseDto tokenResponseDto = tokenService.createTokenInfo(userId);

		return SignInResponseDto.of(userId, tokenResponseDto);
	}

	private String getAppleProfileResponse(String token) {
		return appleTokenProvider.getSocialIdFromIdToken(token);
	}
}
