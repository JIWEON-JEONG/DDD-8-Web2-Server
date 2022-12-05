package ddd.caffeine.ratrip.service;

import ddd.caffeine.ratrip.service.feign.model.KakaoFeignResponseDto;

public interface RecommendPlaceUseCase {
	KakaoFeignResponseDto recommendPlace(String region, String keyword, int page);
}
