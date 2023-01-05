package ddd.caffeine.ratrip.module.feign.auth.kakao.dto;

import lombok.Getter;

@Getter
public class KakaoAccount {
	private Profile profile;
	private String email;
}