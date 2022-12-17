package ddd.caffeine.ratrip.module.recommend.presentation.dto;

import java.util.List;

import ddd.caffeine.ratrip.module.recommend.domain.KakaoFeignData;
import ddd.caffeine.ratrip.module.recommend.domain.KakaoFeignMetaData;
import ddd.caffeine.ratrip.module.recommend.domain.KakaoFeignModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecommendRequestDto {
	private List<KakaoFeignData> documents;
	private KakaoFeignMetaData meta;

	public RecommendRequestDto(KakaoFeignModel kakaoFeignModel) {
		this.documents = kakaoFeignModel.getDocuments();
		this.meta = kakaoFeignModel.getMeta();
	}
}
