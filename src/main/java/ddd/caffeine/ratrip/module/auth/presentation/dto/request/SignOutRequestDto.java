package ddd.caffeine.ratrip.module.auth.presentation.dto.request;

import ddd.caffeine.ratrip.module.auth.application.dto.SignOutDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignOutRequestDto {
	@NotBlank(message = "Access Token must not be blank")
	private String accessToken;

	@NotBlank(message = "Token must not be blank")
	private String refreshToken;

	public SignOutDto toServiceDto() {
		return SignOutDto.of(accessToken, refreshToken);
	}
}
