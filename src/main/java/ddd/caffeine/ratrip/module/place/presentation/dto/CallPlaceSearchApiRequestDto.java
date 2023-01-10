package ddd.caffeine.ratrip.module.place.presentation.dto;

import javax.validation.constraints.NotBlank;

import ddd.caffeine.ratrip.module.place.service.dto.CallPlaceSearchApiDto;
import lombok.Getter;

@Getter
public class CallPlaceSearchApiRequestDto {
	@NotBlank
	private String keyword;

	@NotBlank
	private String latitude;

	@NotBlank
	private String longitude;

	private Integer page;

	public CallPlaceSearchApiRequestDto(String keyword, String latitude, String longitude, Integer page) {
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
