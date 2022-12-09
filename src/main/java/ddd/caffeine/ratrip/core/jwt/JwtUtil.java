package ddd.caffeine.ratrip.core.jwt;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class JwtUtil {
	private final JwtSecretKeyProvider jwtSecretKeyProvider;
	private final RedisTemplate<String, Object> redisTemplate;

	public Long validateTokensAndGetUserId(String accessToken, String refreshToken) {
		validateTokenClaims(refreshToken);
		return getUserIdFromTokens(accessToken, refreshToken);
	}

	private void validateTokenClaims(String token) {
		parseClaim(token);
	}

	private Long getUserIdFromTokens(String accessToken, String refreshToken) {
		Long userId = getUserIdFromAccessToken(accessToken);
		validateExistRefreshToken(refreshToken, userId);
		return userId;
	}

	private void validateExistRefreshToken(String refreshToken, Long userId) {
		Object refreshTokenFromDb = redisTemplate.opsForValue().get("RT:" + userId);

		if (refreshTokenFromDb == null) {
			throw new RuntimeException("NOT_FOUND_REFRESH_TOKEN_EXCEPTION");
		}

		if (!refreshToken.equals(refreshTokenFromDb)) {
			throw new RuntimeException("WRONG_REFRESH_TOKEN_EXCEPTION");
		}
	}

	private Claims parseClaim(String token) {
		try {
			return Jwts.parserBuilder()
				.setSigningKey(jwtSecretKeyProvider.getSecretKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		} catch (SecurityException e) {
			throw new SecurityException("INVALID_JWT_SIGNATURE_EXCEPTION");
		} catch (MalformedJwtException e) {
			throw new MalformedJwtException("INVALID_JWT_TOKEN_EXCEPTION");
		} catch (ExpiredJwtException e) {
			throw new RuntimeException("EXPIRED_JWT_TOKEN_EXCEPTION");
		} catch (UnsupportedJwtException e) {
			throw new UnsupportedJwtException("UNSUPPORTED_JWT_TOKEN_EXCEPTION");
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("NOT_FOUND_JWT_CLAIMS_EXCEPTION");
		}
	}

	public Long getUserIdFromAccessToken(String accessToken) {
		Long userId = parseClaim(accessToken).get(JwtConstants.USER_ID, Long.class);
		validateExistUserIdFromAccessToken(userId);
		return userId;
	}

	private void validateExistUserIdFromAccessToken(Long userId) {
		if (userId == null) {
			throw new RuntimeException("NOT_FOUND_JWT_USERID_EXCEPTION");
		}
	}

	public void validateAccessToken(String accessToken) {
		Long userId = parseClaim(accessToken).get(JwtConstants.USER_ID, Long.class);
		validateExistUserIdFromAccessToken(userId);
	}
}
