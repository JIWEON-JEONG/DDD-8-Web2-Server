package ddd.caffeine.ratrip.module.auth.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SignInWithKakaoDto {
	private String token;

	public static SignInWithKakaoDto of(String token) {
		return new SignInWithKakaoDto(token);
	}
}
