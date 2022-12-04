package ddd.caffeine.ratrip;

import ddd.caffeine.ratrip.service.KakaoFeignResponseDto;

public interface RecommendPlaceUseCase {
	KakaoFeignResponseDto recommendPlace(String region, String keyword);
}
