package ddd.caffeine.ratrip.module.auth.external.apple.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AppleProfileResponse {
	private String id;
	private String name;
	private String email;
}
