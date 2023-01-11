package ddd.caffeine.ratrip.module.place.presentation.dto.search;

import javax.validation.constraints.NotBlank;

import ddd.caffeine.ratrip.module.place.model.ThirdPartySearchOption;
import lombok.Getter;

@Getter
public class PlaceSearchRequestDto {
	@NotBlank(message = "Keyword must not be blank")
	private final String keyword;

	@NotBlank(message = "Latitude must not be blank")
	private final String latitude;

	@NotBlank(message = "Longitude must not be blank")
	private final String longitude;

	private PlaceSearchRequestDto(String keyword, String latitude, String longitude) {
		this.keyword = keyword;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public ThirdPartySearchOption mapByThirdPartySearchOption(int page) {
		return ThirdPartySearchOption.builder()
			.keyword(this.keyword)
			.latitude(this.latitude)
			.longitude(this.longitude)
			.page(page)
			.build();
	}
}
