package ddd.caffeine.ratrip.module.auth.application.dto;

import ddd.caffeine.ratrip.module.user.application.dto.RegisterUserDto;
import ddd.caffeine.ratrip.module.user.domain.UserSocialType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SignUpWithAppleDto {
	private String token;

	private String nickname;

	private String email;

	public static SignUpWithAppleDto of(String token, String nickname, String email) {
		return new SignUpWithAppleDto(token, nickname, email);
	}

	public RegisterUserDto registerUserUsedByAppleAuth(String socialId, UserSocialType socialType) {
		return RegisterUserDto.registerUserUsedByAppleAuth(socialId, nickname, email, socialType);
	}
}
