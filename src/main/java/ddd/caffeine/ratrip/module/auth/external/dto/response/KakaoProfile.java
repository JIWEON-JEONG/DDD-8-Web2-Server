package ddd.caffeine.ratrip.module.auth.external.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoProfile {
	private String id;
	private KakaoAccount kakaoAccount;
}