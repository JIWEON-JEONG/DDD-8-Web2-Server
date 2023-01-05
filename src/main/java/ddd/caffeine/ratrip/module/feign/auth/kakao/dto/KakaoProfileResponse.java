package ddd.caffeine.ratrip.module.feign.auth.kakao.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoProfileResponse {
	private String id;
	private KakaoAccount kakaoAccount;
}