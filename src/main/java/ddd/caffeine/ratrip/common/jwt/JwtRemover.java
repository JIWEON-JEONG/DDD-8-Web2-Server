package ddd.caffeine.ratrip.common.jwt;

import java.util.UUID;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtRemover {
	private final RedisTemplate<String, Object> redisTemplate;

	public void deleteRefreshToken(UUID userId) {
		redisTemplate.delete("RT:" + userId);
	}
}
