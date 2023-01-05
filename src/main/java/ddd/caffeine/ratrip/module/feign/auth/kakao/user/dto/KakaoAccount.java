package ddd.caffeine.ratrip.module.feign.auth.kakao.user.dto;

import lombok.Getter;

@Getter
public class KakaoAccount {
	private Profile profile;
	private String email;
}