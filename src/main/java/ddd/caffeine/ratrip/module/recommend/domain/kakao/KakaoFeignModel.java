package ddd.caffeine.ratrip.module.recommend.domain.kakao;

import java.util.List;

import ddd.caffeine.ratrip.module.recommend.domain.RecommendPlaceData;
import ddd.caffeine.ratrip.module.recommend.domain.kakao.KakaoFeignMetaData;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Todo 디자인 분들과 프론트 분들 필요한 정보 받아서 최적화 할 예정
 */
@Getter
@NoArgsConstructor
public class KakaoFeignModel {
	List<RecommendPlaceData> documents;
	KakaoFeignMetaData meta;
}
