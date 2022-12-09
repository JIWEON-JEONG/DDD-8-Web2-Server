package ddd.caffeine.ratrip.module.auth.presentation.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignInResponseDto {
	private Long userId;
	private TokenResponseDto token;

	public static SignInResponseDto of(Long userId, TokenResponseDto token) {
		return new SignInResponseDto(userId, token);
	}
}
