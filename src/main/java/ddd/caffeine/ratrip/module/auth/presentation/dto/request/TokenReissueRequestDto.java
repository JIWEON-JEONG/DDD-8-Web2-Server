package ddd.caffeine.ratrip.module.auth.presentation.dto.request;

import ddd.caffeine.ratrip.module.auth.application.dto.TokenReissueDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class TokenReissueRequestDto {
	@NotBlank(message = "Access token must not be blank")
	private String accessToken;

	@NotBlank(message = "Refresh token must not be blank")
	private String refreshToken;

	public TokenReissueDto toServiceDto() {
		return TokenReissueDto.of(accessToken, refreshToken);
	}
}