package ddd.caffeine.ratrip.module.external.apple;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ddd.caffeine.ratrip.module.external.apple.dto.AppleProfileResponse;
import ddd.caffeine.ratrip.module.external.apple.dto.ApplePublicKeyResponse;
import ddd.caffeine.ratrip.module.external.exception.ExternalException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppleTokenProvider {
	private final AppleApiClient appleApiClient;
	private final ObjectMapper objectMapper;

	public AppleProfileResponse getSocialIdFromIdToken(String idToken) {
		String headerIdToken = idToken.split("\\.")[0];
		try {
			Map<String, String> header = objectMapper.readValue(
				new String(Base64.getDecoder().decode(headerIdToken), StandardCharsets.UTF_8), new TypeReference<>() {
				});
			PublicKey publicKey = getPublicKey(header);
			Claims claims = Jwts.parserBuilder()
				.setSigningKey(publicKey)
				.build()
				.parseClaimsJws(idToken)
				.getBody();

			claims.get("email");
			return AppleProfileResponse.builder()
				.id(claims.getSubject())
				.name(claims.get("name", String.class))
				.email(claims.get("email", String.class))
				.build();

		} catch (JsonProcessingException | InvalidClaimException e) {
			throw new ExternalException(INVALID_ID_TOKEN_EXCEPTION);
		} catch (ExpiredJwtException e) {
			throw new ExternalException(EXPIRED_ID_TOKEN_EXCEPTION);
		}
	}

	private PublicKey getPublicKey(Map<String, String> header) {

		try {
			ApplePublicKeyResponse response = appleApiClient.getAppleAuthPublicKey();
			ApplePublicKeyResponse.Key key = response.getMatchedPublicKey(header.get("kid"), header.get("alg"));

			byte[] nBytes = Base64.getUrlDecoder().decode(key.getN());
			byte[] eBytes = Base64.getUrlDecoder().decode(key.getE());

			BigInteger n = new BigInteger(1, nBytes);
			BigInteger e = new BigInteger(1, eBytes);

			RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(n, e);
			KeyFactory keyFactory = KeyFactory.getInstance(key.getKty());

			return keyFactory.generatePublic(publicKeySpec);

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new ExternalException(INVALID_PUBLIC_KEY_EXCEPTION);
		}
	}
}

