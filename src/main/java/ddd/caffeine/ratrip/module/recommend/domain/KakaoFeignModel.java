package ddd.caffeine.ratrip.module.recommend.domain;

import java.util.ArrayList;
import java.util.List;

import ddd.caffeine.ratrip.module.place.presentation.dto.PlaceSearchModel;
import ddd.caffeine.ratrip.module.place.presentation.dto.PlaceSearchResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Todo: 디자인 분들과 프론트 분들 필요한 정보 받아서 최적화 할 예정
 */
@Getter
@NoArgsConstructor
public class KakaoFeignModel {
	List<KakaoFeignData> documents;
	KakaoFeignMetaData meta;

	public PlaceSearchResponseDto mapByPlaceSearchResponseDto() {
		List<PlaceSearchModel> searchModels = new ArrayList<>();
		for (KakaoFeignData document : documents) {
			PlaceSearchModel model = PlaceSearchModel.builder()
				.placeName(document.getPlaceName())
				.longitude(document.getX())
				.latitude(document.getY())
				.address(document.getRoadAddressName())
				.build();

			searchModels.add(model);
		}
		return new PlaceSearchResponseDto(searchModels);
	}

}
