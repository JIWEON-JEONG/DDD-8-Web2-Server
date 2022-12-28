package ddd.caffeine.ratrip.module.auth.presentation.dto.request;

import ddd.caffeine.ratrip.module.auth.application.dto.SignInWithKakaoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignInWithKakaoRequestDto {
	@Schema(description = "카카오 엑세스 토큰", example = "k5AmWU6rE9E6FRu92OP40K_MWkrk8TQJu7xaV8VLCj1zTgAAAYUT_N4T")
	@NotBlank(message = "Token must not be blank")
	private String token;

	public SignInWithKakaoDto toServiceDto() {
		return SignInWithKakaoDto.of(token);
	}
}
