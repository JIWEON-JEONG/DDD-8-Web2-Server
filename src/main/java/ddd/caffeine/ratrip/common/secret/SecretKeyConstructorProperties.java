package ddd.caffeine.ratrip.common.secret;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "secret")
public final class SecretKeyConstructorProperties {
	private final String jwt;
	private final Kakao kakao;
	private final Naver naver;

	@Getter
	@RequiredArgsConstructor
	public static final class Kakao {
		private final String restApiKey;
		private final String redirectUri;
	}

	@Getter
	@RequiredArgsConstructor
	public static final class Naver {
		private final String clientKey;
		private final String secretKey;
	}
}
