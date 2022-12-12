package ddd.caffeine.ratrip.module.recommend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ddd.caffeine.ratrip.module.recommend.domain.KakaoFeignModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RecommendPlaceService implements RecommendPlaceUseCase {

	@Value("${KAKAO_API_KEY}")
	private String KAKAO_API_KEY;
	private final KakaoFeignClient kakaoFeignClient;

	private final RecommendPlaceValidator recommendPlaceValidator;

	@Override
	public KakaoFeignModel recommendPlaces(String region, String keyword, int page) {
		recommendPlaceValidator.validatePageSize(page);
		return readPlaces(region, keyword, page);
	}

	private KakaoFeignModel readPlaces(String region, String keyword, int page) {
		final String KAKAO_REQUEST_HEADER = "KakaoAK " + KAKAO_API_KEY;
		String query = region + " " + keyword;
		return kakaoFeignClient.readPlacesByKeywordAndCategory(KAKAO_REQUEST_HEADER, query, page);
	}
}
