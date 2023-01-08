package ddd.caffeine.ratrip.module.auth.application;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.common.util.HttpHeaderUtils;
import ddd.caffeine.ratrip.module.auth.external.KakaoAuthorizeApiClient;
import ddd.caffeine.ratrip.module.auth.external.KakaoUserApiClient;
import ddd.caffeine.ratrip.module.auth.external.dto.request.KakaoBearerTokenRequest;
import ddd.caffeine.ratrip.module.auth.external.dto.response.KakaoBearerTokenResponse;
import ddd.caffeine.ratrip.module.auth.external.dto.response.KakaoProfile;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.SignInResponseDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.TokenResponseDto;
import ddd.caffeine.ratrip.module.user.application.UserService;
import ddd.caffeine.ratrip.module.user.application.dto.SignUpUserDto;
import ddd.caffeine.ratrip.module.user.domain.UserSocialType;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class KakaoAuthService {
	private static final UserSocialType socialType = UserSocialType.KAKAO;
	@Value("${kakao.client-id}")
	private final String kakaoClientId;
	@Value("${kakao.redirect-uri}")
	private final String kakaoRedirectUri;
	private final KakaoAuthorizeApiClient kakaoAuthorizeApiClient;
	private final KakaoUserApiClient kakaoUserApiClient;
	private final UserService userService;
	private final TokenService tokenService;

	public SignInResponseDto signIn(String code) {
		String accessToken = getKakaoAccessToken(code);
		KakaoProfile kakaoProfile = getKakaoProfile(accessToken);
		UUID userId = findUserBySocialIdAndSocialType(kakaoProfile);
		TokenResponseDto tokenResponseDto = tokenService.createTokenInfo(userId);

		return SignInResponseDto.of(userId, tokenResponseDto);
	}

	private String getKakaoAccessToken(String code) {
		KakaoBearerTokenResponse kakaoBearerTokenResponse = kakaoAuthorizeApiClient.getBearerToken(
			KakaoBearerTokenRequest.of(kakaoClientId, kakaoRedirectUri, code));
		return kakaoBearerTokenResponse.getAccessToken();
	}

	private KakaoProfile getKakaoProfile(String token) {
		return kakaoUserApiClient.getKakaoProfile(HttpHeaderUtils.concatWithBearerPrefix(token));
	}

	private UUID findUserBySocialIdAndSocialType(KakaoProfile kakaoProfile) {
		return userService.findUserIdBySocialIdAndSocialType(SignUpUserDto.of(kakaoProfile, socialType));
	}
}