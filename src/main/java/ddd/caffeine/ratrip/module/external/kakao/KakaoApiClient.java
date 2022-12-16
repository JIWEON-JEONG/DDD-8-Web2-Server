package ddd.caffeine.ratrip.module.external.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import ddd.caffeine.ratrip.module.external.kakao.dto.KakaoProfileResponse;

@FeignClient(name = "kakaoApiClient", url = "https://kapi.kakao.com")
public interface KakaoApiClient {
	@GetMapping("/v2/user/me")
	KakaoProfileResponse getProfileInfo(@RequestHeader("Authorization") String accessToken);
}