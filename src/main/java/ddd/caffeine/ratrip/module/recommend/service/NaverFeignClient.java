package ddd.caffeine.ratrip.module.recommend.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import ddd.caffeine.ratrip.common.configuration.FeignConfig;
import ddd.caffeine.ratrip.module.recommend.domain.naver.NaverImageModel;

@FeignClient(name = "NaverFeignClient", url = "https://openapi.naver.com", configuration = FeignConfig.class)
public interface NaverFeignClient {
	@GetMapping(value = "v1/search/image", produces = "application/json")
	NaverImageModel readImageModelByPlaceName(
		@RequestHeader("X-Naver-Client-Id") String clientId,
		@RequestHeader("X-Naver-Client-Secret") String clientSecret,
		@RequestParam("query") String placeName,
		@RequestParam("display") int count,
		@RequestParam("sort") String type
	);
}
