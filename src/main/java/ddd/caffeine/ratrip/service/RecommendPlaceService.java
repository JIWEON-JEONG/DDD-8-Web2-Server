package ddd.caffeine.ratrip.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ddd.caffeine.ratrip.RecommendPlaceUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class RecommendPlaceService implements RecommendPlaceUseCase {

	@Value("${KAKAO_API_KEY}")
	private String KAKAO_API_KEY;
	private final KakaoFeignClient kakaoFeignClient;

	@Override
	public KakaoFeignResponseDto recommendPlace(String region, String keyword) {
		return readPlaces(region, keyword);
	}

	private KakaoFeignResponseDto readPlaces(String region, String keyword) {
		final String KAKAO_REQUEST_HEADER = "KakaoAK " + KAKAO_API_KEY;
		log.info(KAKAO_REQUEST_HEADER);
		String query = region + " " + keyword;
		return kakaoFeignClient.readPlacesByKeywordAndCategory(KAKAO_REQUEST_HEADER, query);
	}
}
