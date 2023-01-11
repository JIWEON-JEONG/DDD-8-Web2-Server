package ddd.caffeine.ratrip.module.place.presentation.dto;

import javax.validation.constraints.NotBlank;

import ddd.caffeine.ratrip.module.place.service.dto.CallPlaceSearchApiDto;
import lombok.Getter;

@Getter
public class PlaceSearchRequestDto {
	@NotBlank(message = "Keyword must not be blank")
	private final String keyword;

	@NotBlank(message = "Latitude must not be blank")
	private final String latitude;

	@NotBlank(message = "Longitude must not be blank")
	private final String longitude;

	private final Integer page;

	private PlaceSearchRequestDto(String keyword, String latitude, String longitude, Integer page) {
		this.keyword = keyword;
		this.latitude = latitude;
		this.longitude = longitude;
		this.page = initializePageValue(page);
	}

	private int initializePageValue(Integer page) {
		if (page == null) {
			return 1;
		}
		return page;
	}

	public CallPlaceSearchApiDto toServiceDto() {
		return CallPlaceSearchApiDto.of(keyword, latitude, longitude, page);
	}
}
