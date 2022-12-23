package ddd.caffeine.ratrip.module.recommend.presentation.dto;

import java.util.List;

import ddd.caffeine.ratrip.module.recommend.domain.RecommendPlaceData;
import ddd.caffeine.ratrip.module.recommend.domain.kakao.KakaoFeignMetaData;
import ddd.caffeine.ratrip.module.recommend.domain.kakao.KakaoFeignModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecommendResponseDto {
	private List<RecommendPlaceData> documents;
	private KakaoFeignMetaData meta;

	public RecommendResponseDto(KakaoFeignModel kakaoFeignModel) {
		this.documents = kakaoFeignModel.getDocuments();
		this.meta = kakaoFeignModel.getMeta();
	}
}
