package ddd.caffeine.ratrip.module.auth.presentation.dto.request;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ddd.caffeine.ratrip.common.exception.domain.AuthException;
import ddd.caffeine.ratrip.module.auth.application.dto.SignInWithAppleDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignInWithAppleRequestDto {
	@Schema(description = "아이디 토큰", example = "k5AmWU6rE9E6FRu92OP40K_MWkrk8TQJu7xaV8VLCj1zTgAAAYUT_N4T")
	@NotBlank(message = "Token must not be blank")
	private String id_token;

	@Schema(description = "인증 코드", example = "cs27903241f4a4cf395ba97456570b048.0.rafsfr.Z6E5z7DOSgRRrZXRmdybDQ")
	@NotBlank(message = "Authorization code must not be blank")
	private String code;

	@Schema(description = "사용자 정보", example = "{\"name\":{\"firstName\":\"페인\",\"lastName\":\"카\"},\"email\":\"caffeineratrip@gmail.com\"}")
	@NotBlank(message = "User must not be blank")
	private String user;

	public SignInWithAppleDto toServiceDto() {
		AppleProfile appleProfile = castStringToAppleUser(user);
		return SignInWithAppleDto.of(id_token, code, appleProfile);
	}

	private AppleProfile castStringToAppleUser(String user) {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			return objectMapper.readValue(user, AppleProfile.class);
		} catch (JsonProcessingException e) {
			throw new AuthException(APPLE_USER_DATA_CASTING_EXCEPTION);
		}
	}
}
