package ddd.caffeine.ratrip.module.place.presentation.dto.search;

import java.util.List;

import ddd.caffeine.ratrip.module.place.feign.kakao.model.FeignPlaceMetaData;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PlaceSearchResponseDto {
	private List<PlaceSearchModel> thirdPartyModel;
	private boolean isEnd;
	private int pageableCount;
	private int totalCount;

	@Builder
	public PlaceSearchResponseDto(List<PlaceSearchModel> placeSearchModels, FeignPlaceMetaData metaData) {
		this.thirdPartyModel = placeSearchModels;
		this.isEnd = metaData.isEnd();
		this.pageableCount = metaData.getPageableCount();
		this.totalCount = metaData.getTotalCount();
	}
}
