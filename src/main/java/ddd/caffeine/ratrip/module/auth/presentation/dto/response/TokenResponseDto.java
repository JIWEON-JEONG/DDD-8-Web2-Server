package ddd.caffeine.ratrip.module.auth.presentation.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TokenResponseDto {
	private String accessToken;
	private String refreshToken;

	public static TokenResponseDto of(String accessToken, String refreshToken) {
		return new TokenResponseDto(accessToken, refreshToken);
	}
}