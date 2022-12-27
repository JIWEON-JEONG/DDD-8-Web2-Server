package ddd.caffeine.ratrip.module.external.apple.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AppleProfileResponse {
	private String id;
	private String name;
	private String email;
}
