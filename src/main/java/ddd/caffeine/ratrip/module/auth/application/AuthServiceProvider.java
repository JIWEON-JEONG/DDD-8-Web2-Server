package ddd.caffeine.ratrip.module.auth.application;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import ddd.caffeine.ratrip.module.auth.application.impl.KakaoAuthService;
import ddd.caffeine.ratrip.module.user.domain.UserSocialType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AuthServiceProvider {
	private static final Map<UserSocialType, AuthService> authServiceMap = new HashMap<>();

	private final KakaoAuthService kakaoAuthService;

	@PostConstruct
	void initializeAuthServicesMap() {
		authServiceMap.put(UserSocialType.KAKAO, kakaoAuthService);
	}

	public AuthService getAuthService(UserSocialType socialType) {
		return authServiceMap.get(socialType);
	}
}