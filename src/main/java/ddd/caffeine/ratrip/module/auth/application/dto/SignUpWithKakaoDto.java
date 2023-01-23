package ddd.caffeine.ratrip.module.auth.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SignUpWithKakaoDto {
	private String token;

	public static SignUpWithKakaoDto of(String token) {
		return new SignUpWithKakaoDto(token);
	}
}
