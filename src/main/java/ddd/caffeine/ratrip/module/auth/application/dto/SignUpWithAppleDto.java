package ddd.caffeine.ratrip.module.auth.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SignUpWithAppleDto {
	private String token;

	private String nickname;

	private String email;

	public static SignUpWithAppleDto of(String token, String nickname, String email) {
		return new SignUpWithAppleDto(token, nickname, email);
	}
}
