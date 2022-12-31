package ddd.caffeine.ratrip.module.feign.domain.place.naver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import ddd.caffeine.ratrip.common.configuration.FeignConfiguration;
import ddd.caffeine.ratrip.module.feign.domain.place.naver.model.ImageNaverModel;

@FeignClient(name = "NaverFeignClient", url = "https://openapi.naver.com", configuration = FeignConfiguration.class)
public interface NaverFeignClient {
	@GetMapping(value = "v1/search/image", produces = "application/json")
	ImageNaverModel readImageModelByPlaceName(
		@RequestHeader("X-Naver-Client-Id") String clientId,
		@RequestHeader("X-Naver-Client-Secret") String clientSecret,
		@RequestParam("query") String placeName,
		@RequestParam("display") int count,
		@RequestParam("sort") String sortType
	);
}
