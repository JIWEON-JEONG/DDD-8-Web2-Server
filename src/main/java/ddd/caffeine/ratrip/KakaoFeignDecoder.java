package ddd.caffeine.ratrip;

import ddd.caffeine.ratrip.exception.domain.KakaoFeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 요청 결과가 5xx 대 에러라면, 요청을 다시 시도 한다.
 */
@Slf4j
@NoArgsConstructor
public class KakaoFeignDecoder implements ErrorDecoder {

	/**
	 * <dl>
	 * <dt>5xx 에러가 지속적으로 발생한다면</dt>
	 * <dd>RetryableException 발생하고, 더이상 Retry 를 할 수 없을때 여기서 throw 한 RetryableException 이 응답으로 내려간다.</dd>
	 * </dl>
	 */
	@Override
	public Exception decode(String methodKey, Response response) {
		final String errorCode = "KAKAO_FEIGN_EXCEPTION";

		log.error("{} 요청이 성공하지 못했습니다. status: {} requestUrl: {}, requestBody: {}, responseBody: {}",
			response.status(), methodKey, response.request().url(), FeignResponseUtils.getRequestBody(response),
			FeignResponseUtils.getResponseBody(response));

		return new KakaoFeignException(response.status(), errorCode, response.reason());
	}
}
