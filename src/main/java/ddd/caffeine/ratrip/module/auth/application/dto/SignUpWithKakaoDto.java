package ddd.caffeine.ratrip.module.auth.application.dto;

import ddd.caffeine.ratrip.module.external.kakao.dto.KakaoProfileResponse;
import ddd.caffeine.ratrip.module.user.application.dto.RegisterUserDto;
import ddd.caffeine.ratrip.module.user.domain.UserSocialType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SignUpWithKakaoDto {
	private String token;

	public static SignUpWithKakaoDto of(String token) {
		return new SignUpWithKakaoDto(token);
	}

	public RegisterUserDto registerUserUsedByKakaoAuth(KakaoProfileResponse kakaoProfileResponse,
		UserSocialType socialType) {
		return RegisterUserDto.registerUserUsedByKakaoAuth(kakaoProfileResponse, socialType);
	}
}
