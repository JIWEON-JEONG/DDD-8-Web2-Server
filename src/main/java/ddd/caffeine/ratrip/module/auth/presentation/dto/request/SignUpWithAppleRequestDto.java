package ddd.caffeine.ratrip.module.auth.presentation.dto.request;

import ddd.caffeine.ratrip.module.auth.application.dto.SignUpWithAppleDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignUpWithAppleRequestDto {
	@Schema(description = "토큰", example = "k5AmWU6rE9E6FRu92OP40K_MWkrk8TQJu7xaV8VLCj1zTgAAAYUT_N4T")
	@NotBlank(message = "Token must not be blank")
	private String idToken;

	@Schema(description = "유저 이름", example = "홍길동")
	private String name;

	@Schema(description = "이메일", example = "example@email.com")
	private String email;

	public SignUpWithAppleDto toServiceDto() {
		return SignUpWithAppleDto.of(idToken, name, email);
	}
}
