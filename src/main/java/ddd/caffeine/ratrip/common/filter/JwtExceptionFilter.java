package ddd.caffeine.ratrip.common.filter;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ddd.caffeine.ratrip.common.exception.CommonException;
import ddd.caffeine.ratrip.common.exception.ExceptionResponse;
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
			respondException(response, e);
		}
	}

	private void respondException(HttpServletResponse response, CommonException e) throws IOException {
		setResponseHeader(response);
		writeResponse(response, e);
	}

	private void setResponseHeader(HttpServletResponse response) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	}

	private void writeResponse(HttpServletResponse response, CommonException e) throws IOException {
		ExceptionResponse exceptionResponse = ExceptionResponse.of(e.getHttpStatus(), e.getErrorCode(), e.getMessage());
		response.getWriter().write(toJson(exceptionResponse));
	}

	private String toJson(ExceptionResponse exceptionResponse) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(exceptionResponse);
	}
}
