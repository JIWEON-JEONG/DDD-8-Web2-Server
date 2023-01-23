package ddd.caffeine.ratrip.module.auth.external.apple;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import ddd.caffeine.ratrip.module.auth.external.apple.dto.response.ApplePublicKeyResponse;

@FeignClient(name = "appleApiClient", url = "https://appleid.apple.com/auth")
public interface AppleApiClient {
	@GetMapping(value = "/keys")
	ApplePublicKeyResponse getAppleAuthPublicKey();
}
