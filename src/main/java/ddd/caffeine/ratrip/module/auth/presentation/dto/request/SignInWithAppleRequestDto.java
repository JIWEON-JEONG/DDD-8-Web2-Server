package ddd.caffeine.ratrip.module.auth.presentation.dto.request;

import javax.validation.constraints.NotBlank;

import ddd.caffeine.ratrip.module.auth.application.dto.SignInWithAppleDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignInWithAppleRequestDto {
	@Schema(description = "아이디 토큰", example = "k5AmWU6rE9E6FRu92OP40K_MWkrk8TQJu7xaV8VLCj1zTgAAAYUT_N4T")
	@NotBlank(message = "Token must not be blank")
	private String idToken;

	@Schema(description = "인증 코드", example = "")
	@NotBlank(message = "Authorization code must not be blank")
	private String authorizationCode;

	@Schema(description = "상태", example = "")
	@NotBlank(message = "State must not be blank")
	private String state;

	@Schema(description = "사용자", example = "")
	@NotBlank(message = "User must not be blank")
	private String user;

	public SignInWithAppleDto toServiceDto() {
		return SignInWithAppleDto.of(idToken, authorizationCode, state, user);
	}
}
