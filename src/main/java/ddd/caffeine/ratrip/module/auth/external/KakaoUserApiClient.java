package ddd.caffeine.ratrip.module.auth.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import ddd.caffeine.ratrip.module.auth.external.dto.response.KakaoProfile;

@FeignClient(name = "kakaoUserApiClient", url = "https://kapi.kakao.com")
public interface KakaoUserApiClient {

	@GetMapping("/v2/user/me")
	KakaoProfile getKakaoProfile(@RequestHeader("Authorization") String accessToken);
}