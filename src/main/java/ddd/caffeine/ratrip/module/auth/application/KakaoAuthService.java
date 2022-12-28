package ddd.caffeine.ratrip.module.auth.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.common.util.HttpHeaderUtils;
import ddd.caffeine.ratrip.module.auth.application.dto.SignInWithKakaoDto;
import ddd.caffeine.ratrip.module.auth.application.dto.SignUpWithKakaoDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.SignInResponseDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.TokenResponseDto;
import ddd.caffeine.ratrip.module.external.kakao.KakaoApiClient;
import ddd.caffeine.ratrip.module.external.kakao.dto.KakaoProfileResponse;
import ddd.caffeine.ratrip.module.user.application.UserService;
import ddd.caffeine.ratrip.module.user.domain.UserSocialType;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class KakaoAuthService {
	private static final UserSocialType socialType = UserSocialType.KAKAO;
	private final KakaoApiClient kakaoApiClient;
	private final UserService userService;
	private final TokenService tokenService;

	public SignInResponseDto signUpWithKakao(SignUpWithKakaoDto request) {
		KakaoProfileResponse kakaoProfileResponse = getKakaoProfileResponse(request.getToken());
		UUID userId = userService.registerUser(request.registerUserUsedByKakaoAuth(kakaoProfileResponse, socialType));
		TokenResponseDto tokenResponseDto = tokenService.createTokenInfo(userId);

		return SignInResponseDto.of(userId, tokenResponseDto);
	}

	public SignInResponseDto signInWithKakao(SignInWithKakaoDto request) {
		KakaoProfileResponse response = getKakaoProfileResponse(request.getToken());
		UUID userId = userService.findUserBySocialIdAndSocialType(response.getId(), socialType);
		TokenResponseDto tokenResponseDto = tokenService.createTokenInfo(userId);

		return SignInResponseDto.of(userId, tokenResponseDto);
	}

	private KakaoProfileResponse getKakaoProfileResponse(String token) {
		return kakaoApiClient.getProfileInfo(HttpHeaderUtils.concatWithBearerPrefix(token));
	}
}