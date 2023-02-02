package ddd.caffeine.ratrip.module.place.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import ddd.caffeine.ratrip.module.place.external.dto.KakaoRegionResponse;

@FeignClient(name = "kakaoRegionApi", url = "https://dapi.kakao.com")
public interface KakaoRegionApiClient {

	@GetMapping("/v2/local/geo/coord2regioncode.json")
	KakaoRegionResponse getRegion(@RequestHeader("Authorization") String requestHeader,
		@RequestParam("x") double longitude, @RequestParam("y") double latitude);
}
