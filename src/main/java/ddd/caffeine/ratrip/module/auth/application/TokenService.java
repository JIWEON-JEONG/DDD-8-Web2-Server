package ddd.caffeine.ratrip.module.auth.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.core.jwt.JwtProvider;
import ddd.caffeine.ratrip.core.jwt.JwtUtil;
import ddd.caffeine.ratrip.module.auth.application.dto.SignOutDto;
import ddd.caffeine.ratrip.module.auth.application.dto.TokenReissueDto;
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

	@Transactional
	public TokenResponseDto reissueToken(TokenReissueDto request) {
		Long userId = jwtUtil.validateTokensAndGetUserId(request.getAccessToken(), request.getRefreshToken());
		return jwtProvider.createTokenInfo(userId);
	}

	@Transactional
	public Long deleteToken(SignOutDto signOutDto) {
		Long userId = jwtUtil.validateTokensAndGetUserId(signOutDto.getAccessToken(), signOutDto.getRefreshToken());
		jwtProvider.deleteRefreshToken(userId);
		return userId;
	}
}
