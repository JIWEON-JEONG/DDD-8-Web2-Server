package ddd.caffeine.ratrip.common.util;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ddd.caffeine.ratrip.common.exception.domain.CoreException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QueryStringArgumentResolver implements HandlerMethodArgumentResolver {

	private final ObjectMapper mapper;

	@Override
	public boolean supportsParameter(final MethodParameter methodParameter) {
		return methodParameter.getParameterAnnotation(QueryStringArgsResolver.class) != null;
	}

	@Override
	public Object resolveArgument(final MethodParameter methodParameter,
		final ModelAndViewContainer modelAndViewContainer,
		final NativeWebRequest nativeWebRequest,
		final WebDataBinderFactory webDataBinderFactory) {

		final HttpServletRequest request = (HttpServletRequest)nativeWebRequest.getNativeRequest();
		final String json = makeJson(request.getQueryString());
		final Object mappedObject;

		try {
			mappedObject = mapper.readValue(json, methodParameter.getParameterType());
		} catch (JsonProcessingException e) {
			throw new CoreException(JSON_TO_OBJECT_MAPPING_EXCEPTION);
		}

		return mappedObject;
	}

	private String makeJson(String a) {
		String jsonForm = "{\"";

		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) == '=') {
				jsonForm += "\"" + ":" + "\"";
			} else if (a.charAt(i) == '&') {
				jsonForm += "\"" + "," + "\"";
			} else {
				jsonForm += a.charAt(i);
			}
		}
		jsonForm += "\"" + "}";
		return jsonForm;
	}
}
