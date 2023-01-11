package ddd.caffeine.ratrip.module.place.presentation.dto.search;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import ddd.caffeine.ratrip.common.validator.RequestValidator;
import ddd.caffeine.ratrip.module.place.model.ThirdPartySearchOption;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlaceSearchRequestDto {
	@NotBlank(message = "Keyword must not be blank")
	private String keyword;

	@NotEmpty(message = "Latitude must not be blank")
	private String latitude;

	@NotEmpty(message = "Longitude must not be blank")
	private String longitude;

	@NotNull(message = "Longitude must not be null")
	private int page;

	private PlaceSearchRequestDto(String keyword, String latitude, String longitude, int page) {
		validateParameters(latitude, longitude, page);
		this.keyword = keyword;
		this.latitude = latitude;
		this.longitude = longitude;
		this.page = page;
	}

	public ThirdPartySearchOption mapByThirdPartySearchOption(int page) {
		return ThirdPartySearchOption.builder()
			.keyword(this.keyword)
			.latitude(this.latitude)
			.longitude(this.longitude)
			.page(page)
			.build();
	}

	private void validateParameters(String latitude, String longitude, int page) {
		RequestValidator.validateRangeLatitude(latitude);
		RequestValidator.validateRangeLongitude(longitude);
		RequestValidator.validatePageSize(page);
	}
}
