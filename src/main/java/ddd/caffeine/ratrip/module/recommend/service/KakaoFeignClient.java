package ddd.caffeine.ratrip.module.recommend.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import ddd.caffeine.ratrip.common.configuration.FeignConfig;
import ddd.caffeine.ratrip.module.recommend.domain.KakaoFeignModel;

@FeignClient(name = "KakaoFeignClient", url = "https://dapi.kakao.com", configuration = FeignConfig.class)
public interface KakaoFeignClient {
	@GetMapping(value = "v2/local/search/keyword.json")
	KakaoFeignModel readPlacesByKeywordAndCategory(
		@RequestHeader("Authorization") String header,
		@RequestParam("query") String query,
		@RequestParam("page") int page
	);
}
