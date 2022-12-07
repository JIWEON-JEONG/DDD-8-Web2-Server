package ddd.caffeine.ratrip;

import java.util.Arrays;
import java.util.Optional;

import ddd.caffeine.ratrip.exception.domain.KakaoFeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class KakaoFeignDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		final String errorCode = "KAKAO_FEIGN_EXCEPTION";

		String requestBody = FeignResponseUtils.encodeRequestBody(response);
		String responseBody = FeignResponseUtils.encodeResponseBody(response);

		log.error("{} 요청이 성공하지 못했습니다. status: {} requestUrl: {}, requestBody: {}, responseBody: {}",
			response.status(), methodKey, response.request().url(), requestBody, responseBody);

		return new KakaoFeignException(response.status(), errorCode, extractExceptionMessage(responseBody));
	}

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
