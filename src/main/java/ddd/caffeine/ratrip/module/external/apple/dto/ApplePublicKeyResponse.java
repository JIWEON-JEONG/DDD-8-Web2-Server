package ddd.caffeine.ratrip.module.external.apple.dto;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import java.util.List;

import ddd.caffeine.ratrip.common.exception.domain.CommonException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplePublicKeyResponse {
	private List<Key> keys;

	public Key getMatchedPublicKey(String kid, String alg) {
		return keys.stream()
			.filter(key -> key.getKid().equals(kid) && key.getAlg().equals(alg))
			.findFirst()
			.orElseThrow(() -> new CommonException(NOT_FOUND_PUBLIC_KEY_EXCEPTION));
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Key {
		private String alg;
		private String e;
		private String kid;
		private String kty;
		private String n;
		private String use;
	}
}
