package ddd.caffeine.ratrip.core.jwt;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import ddd.caffeine.ratrip.core.util.YamlPropertySourceFactory;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
@PropertySource(value = "classpath:application-jwt.yml", factory = YamlPropertySourceFactory.class)
public class JwtSecretKeyProvider {
	private final Key secretKey;

	public JwtSecretKeyProvider(@Value("${jwt.secret}") String secretKey) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.secretKey = Keys.hmacShaKeyFor(keyBytes);
	}

	public Key getSecretKey() {
		return secretKey;
	}
}
