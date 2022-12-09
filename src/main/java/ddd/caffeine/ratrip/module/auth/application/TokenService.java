package ddd.caffeine.ratrip.module.auth.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.core.jwt.JwtProvider;
import ddd.caffeine.ratrip.core.jwt.JwtUtil;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.TokenResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {
	private final JwtProvider jwtProvider;
	private final JwtUtil jwtUtil;

	@Transactional
	public TokenResponseDto createTokenInfo(Long userId) {
		return jwtProvider.createTokenInfo(userId);
	}
}
