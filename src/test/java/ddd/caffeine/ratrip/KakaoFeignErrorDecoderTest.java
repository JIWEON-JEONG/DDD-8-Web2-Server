package ddd.caffeine.ratrip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KakaoFeignErrorDecoderTest {

	KakaoFeignErrorDecoder kakaoFeignErrorDecoder;
	FeignResponseEncoder responseUtils;

	@BeforeEach
	void init() {
		responseUtils = new FeignResponseEncoder();
		kakaoFeignErrorDecoder = new KakaoFeignErrorDecoder(this.responseUtils);
	}

	//    this.status = builder.status;
	//     this.request = builder.request;
	//     this.reason = builder.reason; // nullable
	//     this.headers = caseInsensitiveCopyOf(builder.headers);
	//     this.body = builder.body; // nullable
	//     this.protocolVersion = builder.protocolVersion;
	@Test
	@DisplayName("Response 객체를 제대로 Custom 한 예외 ")
	void decodeTest() {
		// HttpMethod method = GET;
		// String url = "https://test.com";
		// Map<String, Collection<String>> header = new HashMap<>();
		// byte[] requestBody = new byte[100];
		// Request request = create(GET, url, header, requestBody, Charset.defaultCharset(),
		// 	new RequestTemplate());
		//
		// Body responseBody = Body.create()
		//
		// //given
		// Response response = Response.builder()
		// 	.status(500)
		// 	.request(request)
		// 	.reason("Test reason 입니다.")

	}
}