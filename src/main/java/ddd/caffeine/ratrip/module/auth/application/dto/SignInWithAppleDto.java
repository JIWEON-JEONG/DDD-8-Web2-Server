package ddd.caffeine.ratrip.module.auth.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SignInWithAppleDto {
	private String idToken;
	private String authorizationCode;
	private String state;
	private String user;

	public static SignInWithAppleDto of(String idToken, String authorizationCode, String state, String user) {
		return new SignInWithAppleDto(idToken, authorizationCode, state, user);
	}
}
