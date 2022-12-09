package ddd.caffeine.ratrip.core.filter;

import static ddd.caffeine.ratrip.core.util.HttpHeaderUtils.*;

import java.io.IOException;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import ddd.caffeine.ratrip.core.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private static final List<String> whitelist = List.of("v1/auth");
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
		if (isValidHeader(bearerToken)) {
			validateAccessToken(getAccessTokenFromBearer(bearerToken));
		}
	}

	private boolean isValidHeader(String bearerToken) {
		return validateHasText(bearerToken) && validateStartWithBearer(bearerToken);
	}

	private boolean validateHasText(String bearerToken) {
		if (!StringUtils.hasText(bearerToken)) {
			throw new RuntimeException("UNAUTHORIZED_EXCEPTION_EMPTY_HEADER");
		}
		return true;
	}

	private boolean validateStartWithBearer(String bearerToken) {
		if (!bearerToken.startsWith(BEARER_PREFIX)) {
			throw new RuntimeException("UNAUTHORIZED_EXCEPTION_INVALID_BEARER");
		}
		return true;
	}

	private void validateAccessToken(String accessToken) {
		jwtUtil.validateAccessToken(accessToken);
	}

	private String getAccessTokenFromBearer(String bearerToken) {
		return bearerToken.substring(BEARER_PREFIX.length());
	}
}
