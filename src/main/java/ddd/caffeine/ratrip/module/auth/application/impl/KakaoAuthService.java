package ddd.caffeine.ratrip.module.auth.application.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.common.util.HttpHeaderUtils;
import ddd.caffeine.ratrip.module.auth.application.AuthService;
import ddd.caffeine.ratrip.module.auth.application.TokenService;
import ddd.caffeine.ratrip.module.auth.application.dto.SignInDto;
import ddd.caffeine.ratrip.module.auth.application.dto.SignUpDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.SignInResponseDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.TokenResponseDto;
import ddd.caffeine.ratrip.module.external.KakaoApiClient;
import ddd.caffeine.ratrip.module.external.dto.KakaoProfileResponse;
import ddd.caffeine.ratrip.module.user.application.UserService;
import ddd.caffeine.ratrip.module.user.application.dto.RegisterUserDto;
import ddd.caffeine.ratrip.module.user.application.dto.SignInUserDto;
import ddd.caffeine.ratrip.module.user.domain.UserSocialType;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class KakaoAuthService implements AuthService {
	private static final UserSocialType socialType = UserSocialType.KAKAO;
	private final KakaoApiClient kaKaoApiCaller;
	private final UserService userService;
	private final TokenService tokenService;

	@Override
	public SignInResponseDto signUp(SignUpDto request) {
		KakaoProfileResponse kakaoProfileResponse = getKakaoProfileResponse(request.getToken());
		Long userId = userService.registerUser(RegisterUserDto.of(kakaoProfileResponse, socialType));
		TokenResponseDto tokenResponseDto = tokenService.createTokenInfo(userId);

		return SignInResponseDto.of(userId, tokenResponseDto);
	}

	@Override
	public SignInResponseDto signIn(SignInDto request) {
		KakaoProfileResponse response = getKakaoProfileResponse(request.getToken());
		Long userId = userService.findUserBySocialIdAndSocialType(SignInUserDto.of(response.getId(), socialType));
		TokenResponseDto tokenResponseDto = tokenService.createTokenInfo(userId);

		return SignInResponseDto.of(userId, tokenResponseDto);
	}

	private KakaoProfileResponse getKakaoProfileResponse(String token) {
		return kaKaoApiCaller.getProfileInfo(HttpHeaderUtils.concatWithBearerPrefix(token));
	}
}