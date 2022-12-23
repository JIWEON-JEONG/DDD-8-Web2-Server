package ddd.caffeine.ratrip.module.recommend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ddd.caffeine.ratrip.module.recommend.domain.KakaoFeignModel;
import ddd.caffeine.ratrip.module.recommend.domain.KakaoPlaceData;
import ddd.caffeine.ratrip.module.recommend.domain.NaverImageModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RecommendPlaceService {

	@Value("${KAKAO_API_KEY}")
	private String KAKAO_API_KEY;

	@Value("${NAVER_ID}")
	private String NAVER_ID;

	@Value("${NAVER_SECRET}")
	private String NAVER_SECRET;

	private final KakaoFeignClient kakaoFeignClient;
	private final NaverFeignClient naverFeignClient;
	private final RecommendPlaceValidator recommendPlaceValidator;

	public KakaoFeignModel recommendPlaces(String region, String keyword, int page) {
		recommendPlaceValidator.validatePageSize(page);
		KakaoFeignModel missingImagePlaces = readPlaces(region, keyword, page);
		return injectImageModelEachPlace(missingImagePlaces);
	}

	private KakaoFeignModel readPlaces(String region, String keyword, int page) {
		final String KAKAO_REQUEST_HEADER = "KakaoAK " + KAKAO_API_KEY;
		String query = region + " " + keyword;
		return kakaoFeignClient.readPlacesByKeywordAndCategory(KAKAO_REQUEST_HEADER, query, page);
	}

	/**
	 * Naver API - 너무 많은 요청 Error
	 */
	private KakaoFeignModel injectImageModelEachPlace(KakaoFeignModel kakaoFeignModel) {
		final int data_count = 1;
		final String sort = "sim";

		List<KakaoPlaceData> documents = kakaoFeignModel.getDocuments();
		for (int i = 0; i < documents.size(); i++) {
			String placeName = readPlaceName(documents.get(i));
			NaverImageModel naverImageModel = naverFeignClient.readImageModelByPlaceName(
				NAVER_ID, NAVER_SECRET, placeName, data_count, sort
			);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			documents.get(i).injectImageModel(naverImageModel);
		}

		return kakaoFeignModel;
	}

	private String readPlaceName(KakaoPlaceData kakaoPlaceData) {
		return kakaoPlaceData.getPlaceName();
	}
}
