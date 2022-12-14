package ddd.caffeine.ratrip.module.recommend.presentation.dto;

import java.util.List;

import ddd.caffeine.ratrip.module.recommend.domain.KakaoFeignMetaData;
import ddd.caffeine.ratrip.module.recommend.domain.KakaoFeignModel;
import ddd.caffeine.ratrip.module.recommend.domain.KakaoPlaceData;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecommendResponseDto {
	private List<KakaoPlaceData> documents;
	private KakaoFeignMetaData meta;

	public RecommendResponseDto(KakaoFeignModel kakaoFeignModel) {
		this.documents = kakaoFeignModel.getDocuments();
		this.meta = kakaoFeignModel.getMeta();
	}
}
