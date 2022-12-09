package ddd.caffeine.ratrip.module.auth.presentation.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SignInResponseDto {
	private Long userId;
	private TokenResponseDto token;

	public static SignInResponseDto of(Long userId, TokenResponseDto token) {
		return new SignInResponseDto(userId, token);
	}
}
