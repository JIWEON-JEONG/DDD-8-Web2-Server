package ddd.caffeine.ratrip.common.filter;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;
import static ddd.caffeine.ratrip.common.util.HttpHeaderUtils.*;

import java.io.IOException;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import ddd.caffeine.ratrip.common.exception.domain.CommonException;
import ddd.caffeine.ratrip.common.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private static final List<String> whitelist = List.of("/auth", "/swagger-ui", "/api-docs", "/test");
	private final JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		if (!isWhiteList(request.getRequestURI())) {
			String bearerToken = request.getHeader(AUTHORIZATION_HEADER_PREFIX);
			validateHeader(bearerToken);
		}

		filterChain.doFilter(request, response);
	}

	private boolean isWhiteList(String requestURI) {
		return whitelist.stream().anyMatch(requestURI::contains);
	}

	private void validateHeader(String bearerToken) {
		validateHasText(bearerToken);
		validateStartWithBearer(bearerToken);
		validateAccessToken(getAccessTokenFromBearer(bearerToken));
	}

	private void validateHasText(String bearerToken) {
		if (!StringUtils.hasText(bearerToken)) {
			throw new CommonException(EMPTY_HEADER_EXCEPTION);
		}
	}

	private void validateStartWithBearer(String bearerToken) {
		if (!bearerToken.startsWith(BEARER_PREFIX)) {
			throw new CommonException(INVALID_BEARER_FORMAT_EXCEPTION);
		}
	}

	private void validateAccessToken(String accessToken) {
		jwtUtil.validateAccessToken(accessToken);
	}

	private String getAccessTokenFromBearer(String bearerToken) {
		return bearerToken.substring(BEARER_PREFIX.length());
	}
}
