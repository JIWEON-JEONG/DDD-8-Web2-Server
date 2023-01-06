package ddd.caffeine.ratrip.module.auth.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.common.util.HttpHeaderUtils;
import ddd.caffeine.ratrip.module.auth.application.dto.SignUpDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.SignInResponseDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.TokenResponseDto;
import ddd.caffeine.ratrip.module.feign.auth.kakao.user.KakaoUserApiClient;
import ddd.caffeine.ratrip.module.feign.auth.kakao.user.dto.KakaoProfileResponse;
import ddd.caffeine.ratrip.module.user.application.UserService;
import ddd.caffeine.ratrip.module.user.application.dto.RegisterUserDto;
import ddd.caffeine.ratrip.module.user.application.dto.SignInUserDto;
import ddd.caffeine.ratrip.module.user.domain.UserSocialType;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class KakaoAuthService {
	private static final UserSocialType socialType = UserSocialType.KAKAO;
	private final KakaoUserApiClient kaKaoApiCaller;
	private final UserService userService;
	private final TokenService tokenService;

	public SignInResponseDto signUp(SignUpDto request) {
		KakaoProfileResponse kakaoProfileResponse = getKakaoProfileResponse(request.getToken());
		UUID userId = userService.registerUser(RegisterUserDto.of(kakaoProfileResponse, socialType));
		TokenResponseDto tokenResponseDto = tokenService.createTokenInfo(userId);

		return SignInResponseDto.of(userId, tokenResponseDto);
	}

	public SignInResponseDto signIn() {
		KakaoProfileResponse response = getKakaoProfileResponse();
		UUID userId = userService.findUserBySocialIdAndSocialType(SignInUserDto.of(response.getId(), socialType));
		TokenResponseDto tokenResponseDto = tokenService.createTokenInfo(userId);

		return SignInResponseDto.of(userId, tokenResponseDto);
	}

	private KakaoProfileResponse getKakaoProfileResponse(String token) {
		return kaKaoApiCaller.getProfileInfo(HttpHeaderUtils.concatWithBearerPrefix(token));
	}
}