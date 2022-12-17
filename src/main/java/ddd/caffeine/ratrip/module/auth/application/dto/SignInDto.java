package ddd.caffeine.ratrip.module.auth.application.dto;

import ddd.caffeine.ratrip.module.user.domain.UserSocialType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SignInDto {
	private String token;
	private UserSocialType socialType;

	public static SignInDto of(String token, UserSocialType socialType) {
		return new SignInDto(token, socialType);
	}
}
