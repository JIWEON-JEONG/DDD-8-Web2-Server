package ddd.caffeine.ratrip.module.recommend.service;

import java.util.Arrays;
import java.util.Optional;

import ddd.caffeine.ratrip.common.exception.domain.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class FeignErrorDecoder implements ErrorDecoder {

	private final FeignResponseEncoder feignResponseEncoder;

	@Override
	public Exception decode(String methodKey, Response response) {
		final String errorCode = "FEIGN_EXCEPTION";

		String requestBody = feignResponseEncoder.encodeRequestBody(response);
		String responseBody = feignResponseEncoder.encodeResponseBody(response);

		log.error("요청이 성공하지 못했습니다. status: {} requestUrl: {}, requestBody: {}, responseBody: {}",
			response.status(), response.request().url(), requestBody, responseBody);

		return new FeignException(response.status(), errorCode, extractExceptionMessage(responseBody));
	}

	/**
	 * responseBody 예시 - "{errorType:InvalidArgument,message:page is more than max}";
	 * 이부분에서 message 만 추출 하기 위한 메서드.
	 *
	 * @param response
	 * @return 예외 message
	 */
	private String extractExceptionMessage(String response) {
		String messageBlock = extractMessageBlock(removeMiddleBracket(response));
		if (messageBlock.isEmpty()) {
			return messageBlock;
		}
		return extractMessage(messageBlock);
	}

	private String extractMessage(String messageBlock) {
		String[] splitResponse = messageBlock.split(":");
		String message = splitResponse[1];
		return message.replaceAll("\"", "");
	}

	private String removeMiddleBracket(String response) {
		return response.substring(1, response.length() - 1);
	}

	private String extractMessageBlock(String response) {
		String[] splitResponse = response.split(",");
		Optional<String> message = Arrays.stream(splitResponse).filter(
			split -> split.contains("message")).findFirst();
		if (message.isEmpty()) {
			return "";
		}
		return message.get();
	}

}
