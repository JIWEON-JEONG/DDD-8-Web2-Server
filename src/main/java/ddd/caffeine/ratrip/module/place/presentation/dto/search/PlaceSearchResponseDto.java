package ddd.caffeine.ratrip.module.place.presentation.dto.search;

import java.util.List;

import ddd.caffeine.ratrip.module.place.feign.kakao.model.KakaoFeignMetaData;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PlaceSearchResponseDto {
	private List<PlaceSearchModel> placeSearchModels;

	private boolean isEnd;
	private int pageableCount;
	private int totalCount;

	@Builder
	public PlaceSearchResponseDto(List<PlaceSearchModel> placeSearchModels, KakaoFeignMetaData metaData) {
		this.placeSearchModels = placeSearchModels;
		this.isEnd = metaData.isEnd();
		this.pageableCount = metaData.getPageableCount();
		this.totalCount = metaData.getTotalCount();
	}
}
