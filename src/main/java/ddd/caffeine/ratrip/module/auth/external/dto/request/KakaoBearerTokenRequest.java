package ddd.caffeine.ratrip.module.auth.external.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoBearerTokenRequest {
	private static final String GRANT_TYPE = "authorization_code";
	private final String grantType;
	private final String clientId;
	private final String redirectUri;
	private final String code;

	public static KakaoBearerTokenRequest of(String clientId, String redirectUri, String code) {
		return new KakaoBearerTokenRequest(GRANT_TYPE, clientId, redirectUri, code);
	}
}
