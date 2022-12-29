package ddd.caffeine.ratrip.module.place.presentation.dto;

import java.util.List;

import ddd.caffeine.ratrip.module.recommend.domain.KakaoFeignData;
import ddd.caffeine.ratrip.module.recommend.domain.KakaoFeignMetaData;
import ddd.caffeine.ratrip.module.recommend.domain.KakaoFeignModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecommendResponseDto {
	private List<KakaoFeignData> documents;
	private KakaoFeignMetaData meta;

	public RecommendResponseDto(KakaoFeignModel kakaoFeignModel) {
		this.documents = kakaoFeignModel.getDocuments();
		this.meta = kakaoFeignModel.getMeta();
	}
}
