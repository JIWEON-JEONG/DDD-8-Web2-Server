package ddd.caffeine.ratrip.module.auth.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.common.jwt.JwtProvider;
import ddd.caffeine.ratrip.common.jwt.JwtRemover;
import ddd.caffeine.ratrip.common.jwt.JwtUtil;
import ddd.caffeine.ratrip.module.auth.application.dto.SignOutDto;
import ddd.caffeine.ratrip.module.auth.application.dto.TokenReissueDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.TokenResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TokenService {
	private final JwtRemover jwtRemover;
	private final JwtProvider jwtProvider;
	private final JwtUtil jwtUtil;

	public TokenResponseDto createTokenInfo(UUID userId) {
		return jwtProvider.createTokenInfo(userId);
	}

	public TokenResponseDto reissueToken(TokenReissueDto request) {
		UUID userId = jwtUtil.validateTokensAndGetUserId(request.getAccessToken(), request.getRefreshToken());
		return jwtProvider.createTokenInfo(userId);
	}

	public UUID deleteToken(SignOutDto signOutDto) {
		UUID userId = jwtUtil.validateTokensAndGetUserId(signOutDto.getAccessToken(), signOutDto.getRefreshToken());
		jwtRemover.deleteRefreshToken(userId);
		return userId;
	}
}
