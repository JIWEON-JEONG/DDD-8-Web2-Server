package ddd.caffeine.ratrip.module.auth.application;

import static ddd.caffeine.ratrip.module.user.domain.UserSocialType.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import ddd.caffeine.ratrip.module.auth.application.impl.AppleAuthService;
import ddd.caffeine.ratrip.module.auth.application.impl.KakaoAuthService;
import ddd.caffeine.ratrip.module.user.domain.UserSocialType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AuthServiceProvider {
	private static final Map<UserSocialType, AuthService> authServiceMap = new HashMap<>();

	private final KakaoAuthService kakaoAuthService;
	private final AppleAuthService appleAuthService;

	@PostConstruct
	void initializeAuthServicesMap() {
		authServiceMap.put(KAKAO, kakaoAuthService);
		authServiceMap.put(APPLE, appleAuthService);
	}

	public AuthService getAuthService(UserSocialType socialType) {
		return authServiceMap.get(socialType);
	}
}