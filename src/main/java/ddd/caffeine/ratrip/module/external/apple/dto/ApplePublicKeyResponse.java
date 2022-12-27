package ddd.caffeine.ratrip.module.external.apple.dto;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import java.util.List;

import ddd.caffeine.ratrip.common.exception.domain.CommonException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor //가 있어야 AppleApiClient가 매칭해주지 않을까?
public class ApplePublicKeyResponse {
	private List<Key> keys;

	public Key getMatchedPublicKey(String kid, String alg) {
		return keys.stream()
			.filter(key -> key.getKid().equals(kid) && key.getAlg().equals(alg))
			.findFirst()
			.orElseThrow(() -> new CommonException(NOT_FOUND_PUBLIC_KEY_EXCEPTION)); //TODO - 도메인별 예외처리
	}

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Key {
		private String alg;
		private String e;
		private String kid;
		private String kty;
		private String n;
		private String use;
	}
}
