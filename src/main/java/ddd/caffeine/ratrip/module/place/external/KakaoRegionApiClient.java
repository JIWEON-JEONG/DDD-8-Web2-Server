package ddd.caffeine.ratrip.module.place.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import ddd.caffeine.ratrip.module.place.external.dto.KakaoRegionResponse;

@FeignClient(name = "kakaoRegionApi", url = "https://dapi.kakao.com")
public interface KakaoRegionApiClient {

	@GetMapping("/v2/local/geo/coord2regioncode.json")
	KakaoRegionResponse getRegion(double x, double y);
}
