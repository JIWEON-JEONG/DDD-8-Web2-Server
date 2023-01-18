package ddd.caffeine.ratrip.module.place.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ThirdPartySearchOption {
	private String keyword;

	private String latitude;

	private String longitude;

	private int page;
}
