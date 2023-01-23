package ddd.caffeine.ratrip.module.auth.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.common.util.AppleTokenProvider;
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

	public String getAppleProfileResponse(String idToken) {
		return appleTokenProvider.getSocialIdFromIdToken(idToken);
	}
}
