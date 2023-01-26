package ddd.caffeine.ratrip.module.auth.application.dto;

import ddd.caffeine.ratrip.module.auth.presentation.dto.request.AppleProfile;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SignInWithAppleDto {
	private String idToken;
	private String authorizationCode;
	private AppleProfile user;

	public static SignInWithAppleDto of(String idToken, String authorizationCode, AppleProfile user) {
		return new SignInWithAppleDto(idToken, authorizationCode, user);
	}
}
