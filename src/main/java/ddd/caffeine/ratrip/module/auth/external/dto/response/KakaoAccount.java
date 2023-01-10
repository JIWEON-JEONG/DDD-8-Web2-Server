package ddd.caffeine.ratrip.module.auth.external.dto.response;

import lombok.Getter;

@Getter
public class KakaoAccount {
	private Profile profile;
	private String email;
}