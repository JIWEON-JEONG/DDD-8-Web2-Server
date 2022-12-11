package ddd.caffeine.ratrip.module.recommend.service;

import ddd.caffeine.ratrip.module.recommend.domain.KakaoFeignModel;

public interface RecommendPlaceUseCase {
	KakaoFeignModel recommendPlaces(String region, String keyword, int page);
}
