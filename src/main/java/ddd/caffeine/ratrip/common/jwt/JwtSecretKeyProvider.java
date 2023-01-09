package ddd.caffeine.ratrip.common.jwt;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import ddd.caffeine.ratrip.common.util.YamlPropertySourceFactory;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
@PropertySource(value = "classpath:application-jwt.yml", factory = YamlPropertySourceFactory.class)
public class JwtSecretKeyProvider {
	private final Key secretKey;

	public JwtSecretKeyProvider(@Value("${JWT_SECRET_KEY}") String secretKey) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.secretKey = Keys.hmacShaKeyFor(keyBytes);
	}

	public Key getSecretKey() {
		return secretKey;
	}
}
