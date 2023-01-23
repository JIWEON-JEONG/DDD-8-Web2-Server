package ddd.caffeine.ratrip.common.util;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import ddd.caffeine.ratrip.common.exception.domain.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class FeignErrorDecoder implements ErrorDecoder {

	private final FeignResponseEncoder feignResponseEncoder;

	@Override
	public Exception decode(String methodKey, Response response) {
		final String EXCEPTION_CODE = "FEIGN_EXCEPTION";
		final int RETRY_STATUS = 429;

		if (response.status() == RETRY_STATUS) {
			log.info("retry 를 시도합니다.");
			throw new RetryableException(response.status(), response.reason(),
				response.request().httpMethod(), new Date(System.currentTimeMillis()), response.request());
		}
		String requestBody = feignResponseEncoder.encodeRequestBody(response).toUpperCase();
		String responseBody = feignResponseEncoder.encodeResponseBody(response).toUpperCase();

		log.error("요청이 성공하지 못했습니다. status: {} requestUrl: {}, requestBody: {}, responseBody: {}",
			response.status(), response.request().url(), requestBody, responseBody);

		String errorMessage = extractExceptionMessage(responseBody);
		return new FeignException(response.status(), EXCEPTION_CODE, errorMessage);
	}

	/**
	 * naver responseBody : {"errorMessage":"Rate limit exceeded. (속도 제한을 초과했습니다.)", "errorCode":"012"}
	 * kakao responseBody : "{errorType:InvalidArgument, message:page is more than max}"
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

	// JSON 중괄호를 삭제하기 위한 메서드.
	private String removeMiddleBracket(String response) {
		return response.substring(1, response.length() - 1);
	}

	private String extractMessageBlock(String response) {
		final String KEYWORD = "MESSAGE";
		String[] splitResponse = response.split(",");
		Optional<String> message = Arrays.stream(splitResponse).filter(
			split -> split.contains(KEYWORD)).findFirst();

		return message.orElse("");
	}
}
