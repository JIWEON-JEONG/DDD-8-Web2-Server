package ddd.caffeine.ratrip.module.auth.presentation.dto.request;

import ddd.caffeine.ratrip.module.auth.application.dto.SignUpDto;
import ddd.caffeine.ratrip.module.user.domain.UserSocialType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SignUpRequestDto {
	@NotBlank(message = "Token must not be blank")
	private String token;

	@NotNull(message = "Social type must not be null")
	private UserSocialType socialType;

	public SignUpDto toServiceDto() {
		return SignUpDto.of(token, socialType);
	}
}