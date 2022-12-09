package ddd.caffeine.ratrip.module.auth.presentation.dto.request;

import ddd.caffeine.ratrip.module.auth.application.dto.SignInDto;
import ddd.caffeine.ratrip.module.user.domain.UserSocialType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SignInRequestDto {
	@NotBlank(message = "Token must not be blank")
	private String token;

	@NotNull(message = "Social type must not be null")
	private UserSocialType socialType;

	public SignInDto toServiceDto() {
		return SignInDto.of(token, socialType);
	}
}
