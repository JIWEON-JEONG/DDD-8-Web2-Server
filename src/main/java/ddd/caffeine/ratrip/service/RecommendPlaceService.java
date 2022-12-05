package ddd.caffeine.ratrip.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ddd.caffeine.ratrip.exception.ExceptionInformation;
import ddd.caffeine.ratrip.exception.domain.KakaoFeignException;
import ddd.caffeine.ratrip.service.feign.KakaoFeignClient;
import ddd.caffeine.ratrip.service.feign.model.KakaoFeignResponseDto;
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
	public KakaoFeignResponseDto recommendPlace(String region, String keyword, int page) {
		return readPlaces(region, keyword, page);
	}

	private KakaoFeignResponseDto readPlaces(String region, String keyword, int page) {
		final String KAKAO_REQUEST_HEADER = "KakaoAK " + KAKAO_API_KEY;
		log.info(KAKAO_REQUEST_HEADER);
		String query = region + " " + keyword;
		return kakaoFeignClient.readPlacesByKeywordAndCategory(KAKAO_REQUEST_HEADER, query, page);
	}

	private void validatePageSize(int page) {
		final int MIN_PAGE = 1;
		final int MAX_PAGE = 45;
		if (page < MIN_PAGE || page > MAX_PAGE) {
			throw new KakaoFeignException(ExceptionInformation.KAKAO_PAGE_NUMBER_EXCEPTION);
		}
	}
}
