package ddd.caffeine.ratrip.module.auth.presentation.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import ddd.caffeine.ratrip.module.auth.application.dto.SignInDto;
import ddd.caffeine.ratrip.module.user.domain.UserSocialType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignInRequestDto {
	@Schema(description = "카카오 토큰", example = "k5AmWU6rE9E6FRu92OP40K_MWkrk8TQJu7xaV8VLCj1zTgAAAYUT_N4T")
	@NotBlank(message = "Token must not be blank")
	private String token;

	@Schema(description = "소셜 타입", example = "KAKAO", allowableValues = {"KAKAO", "APPLE"})
	@NotNull(message = "Social type must not be null")
	private UserSocialType socialType;

	public SignInDto toServiceDto() {
		return SignInDto.of(token, socialType);
	}
}
