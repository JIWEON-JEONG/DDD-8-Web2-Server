package ddd.caffeine.ratrip.module.external.apple;

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

import ddd.caffeine.ratrip.module.external.apple.dto.ApplePublicKeyResponse;
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

	public String getSocialIdFromIdToken(String idToken) {
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
			return claims.getSubject(); // return socialId;
		} catch (JsonProcessingException | InvalidKeySpecException | InvalidClaimException |
				 NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(
				String.format("잘못된 애플 idToken (%s) 입니다 (reason: %s)", idToken, e.getMessage()));
		} catch (ExpiredJwtException e) {
			throw new IllegalArgumentException(
				String.format("만료된 애플 idToken (%s) 입니다 (reason: %s)", idToken, e.getMessage()));
		}
	}

	private PublicKey getPublicKey(Map<String, String> header) throws
		InvalidKeySpecException,
		NoSuchAlgorithmException {
		ApplePublicKeyResponse response = appleApiClient.getAppleAuthPublicKey();
		ApplePublicKeyResponse.Key key = response.getMatchedPublicKey(header.get("kid"), header.get("alg"));

		byte[] nBytes = Base64.getUrlDecoder().decode(key.getN());
		byte[] eBytes = Base64.getUrlDecoder().decode(key.getE());

		BigInteger n = new BigInteger(1, nBytes);
		BigInteger e = new BigInteger(1, eBytes);

		RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(n, e);
		KeyFactory keyFactory = KeyFactory.getInstance(key.getKty());
		return keyFactory.generatePublic(publicKeySpec);
	}
}

