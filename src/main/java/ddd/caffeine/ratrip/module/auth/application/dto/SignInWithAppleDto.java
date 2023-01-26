package ddd.caffeine.ratrip.module.auth.application.dto;

import ddd.caffeine.ratrip.module.auth.presentation.dto.request.AppleUserData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SignInWithAppleDto {
	private String idToken;
	private String authorizationCode;
	private AppleUserData user;

	public static SignInWithAppleDto of(String idToken, String authorizationCode, AppleUserData user) {
		return new SignInWithAppleDto(idToken, authorizationCode, user);
	}
}
