package ddd.caffeine.ratrip.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpHeaderUtils {
	public static final String BEARER_PREFIX = "Bearer ";
	public static final String AUTHORIZATION_HEADER_PREFIX = "Authorization";

	public static String concatWithBearerPrefix(String token) {
		return BEARER_PREFIX.concat(token);
	}
}