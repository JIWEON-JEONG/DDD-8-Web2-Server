package ddd.caffeine.ratrip.module.user.application.dto;

import ddd.caffeine.ratrip.module.external.apple.dto.AppleProfileResponse;
import ddd.caffeine.ratrip.module.external.kakao.dto.KakaoProfileResponse;
import ddd.caffeine.ratrip.module.user.domain.UserSocialType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisterUserDto {
	private String socialId;
	private UserSocialType socialType;
	private String name;
	private String email;

	@Builder(access = AccessLevel.PRIVATE)
	private RegisterUserDto(String socialId, UserSocialType socialType, String name, String email) {
		this.socialId = socialId;
		this.socialType = socialType;
		this.name = name;
		this.email = email;
	}

	public static RegisterUserDto withKakakResponse(KakaoProfileResponse kakaoProfileResponse,
		UserSocialType socialType) {
		return RegisterUserDto.builder()
			.socialId(kakaoProfileResponse.getId())
			.socialType(socialType)
			.name(kakaoProfileResponse.getKakaoAccount().getProfile().getNickname())
			.email(kakaoProfileResponse.getKakaoAccount().getEmail())
			.build();
	}

	public static RegisterUserDto withAppleResponse(AppleProfileResponse appleProfileResponse, UserSocialType socialType) {
		return RegisterUserDto.builder()
			.socialId(appleProfileResponse.getId())
			.socialType(socialType)
			.build();
	}
}