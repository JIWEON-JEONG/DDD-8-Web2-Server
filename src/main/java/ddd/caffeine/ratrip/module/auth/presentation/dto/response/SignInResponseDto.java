package ddd.caffeine.ratrip.module.auth.presentation.dto.response;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SignInResponseDto {
	private UUID userId;
	private TokenResponseDto token;

	public static SignInResponseDto of(UUID userId, TokenResponseDto token) {
		return new SignInResponseDto(userId, token);
	}
}
