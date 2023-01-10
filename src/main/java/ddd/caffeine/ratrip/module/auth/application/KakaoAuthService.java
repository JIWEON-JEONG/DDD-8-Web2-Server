package ddd.caffeine.ratrip.module.auth.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.common.util.HttpHeaderUtils;
import ddd.caffeine.ratrip.module.auth.external.KakaoAuthorizeApiClient;
import ddd.caffeine.ratrip.module.auth.external.KakaoUserApiClient;
import ddd.caffeine.ratrip.module.auth.external.dto.request.KakaoBearerTokenRequest;
import ddd.caffeine.ratrip.module.auth.external.dto.response.KakaoBearerTokenResponse;
import ddd.caffeine.ratrip.module.auth.external.dto.response.KakaoProfile;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class KakaoAuthService {
	@Value("${KAKAO_API_KEY}")
	private String kakaoClientId;
	@Value("${KAKAO_REDIRECT_URI}")
	private String kakaoRedirectUri;
	private final KakaoAuthorizeApiClient kakaoAuthorizeApiClient;
	private final KakaoUserApiClient kakaoUserApiClient;

	public KakaoProfile getKakaoProfile(String authorizationCode) {
		String accessToken = getKakaoAccessToken(authorizationCode);
		return kakaoUserApiClient.getKakaoProfile(HttpHeaderUtils.concatWithBearerPrefix(accessToken));
	}

	public String getKakaoAccessToken(String authorizationCode) {
		KakaoBearerTokenResponse kakaoBearerTokenResponse = kakaoAuthorizeApiClient.getBearerToken(
			KakaoBearerTokenRequest.of(kakaoClientId, kakaoRedirectUri, authorizationCode));
		return kakaoBearerTokenResponse.getAccessToken();
	}
}