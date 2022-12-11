package ddd.caffeine.ratrip;

import static java.lang.String.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import feign.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 외부 API 통신 에서 Response 객체에 대한 Util Class.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Component
public class FeignResponseUtils {
	public String encodeRequestBody(Response response) {
		if (response.request().body() == null) {
			return "";
		}
		try {
			return new String(response.request().body(), StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			log.error(format("feign request body converting error - response: %s", response), e);
			return "";
		}
	}

	public String encodeResponseBody(Response response) {
		if (response.body() == null) {
			return "";
		}
		try (InputStream responseBodyStream = response.body().asInputStream()) {
			return IOUtils.toString(responseBodyStream, StandardCharsets.UTF_8.name());
		} catch (IOException e) {
			log.error(format("feign response body converting error - response: %s", response), e);
			return "";
		}
	}
}
