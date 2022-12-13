package ddd.caffeine.ratrip.common.filter;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import ddd.caffeine.ratrip.common.exception.CommonException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtExceptionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (CommonException e) {
			
		}
	}
}
