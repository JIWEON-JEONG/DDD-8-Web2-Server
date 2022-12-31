package ddd.caffeine.ratrip.module.feign.domain.place;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ddd.caffeine.ratrip.module.feign.domain.place.kakao.KakaoFeignClient;
import ddd.caffeine.ratrip.module.feign.domain.place.kakao.PlaceKakaoModel;
import ddd.caffeine.ratrip.module.feign.domain.place.naver.ImageNaverModel;
import ddd.caffeine.ratrip.module.feign.domain.place.naver.NaverFeignClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceFeignService {

	@Value("${KAKAO_API_KEY}")
	private String KAKAO_API_KEY;
	@Value("${NAVER_ID}")
	private String NAVER_ID;
	@Value("${NAVER_SECRET}")
	private String NAVER_SECRET;

	private final KakaoFeignClient kakaoFeignClient;
	private final NaverFeignClient naverFeignClient;

	/**
	 * 주소와 장소이름을 토대로 하나의 장소를 읽어옵니다.
	 */
	public PlaceKakaoModel readOnePlace(String address, String placeName) {
		final String KAKAO_REQUEST_HEADER = "KakaoAK " + KAKAO_API_KEY;
		String keyword = address + " " + placeName;
		final int DATA_COUNT = 1;

		return kakaoFeignClient.readPlaceByKeyword(KAKAO_REQUEST_HEADER, keyword,
			DATA_COUNT);
	}

	/**
	 * keyword 를 토대로 주변 5km 내의 장소들을 읽어옵니다.
	 * @Todo : 반경 몇 km 까지 할 것인지 프론트 개발자님들과 상의.
	 */
	public PlaceKakaoModel readPlaces(String keyword, String latitude, String longitude, int page) {
		final String KAKAO_REQUEST_HEADER = "KakaoAK " + KAKAO_API_KEY;
		final int PLACE_RADIUS = 5000;

		return kakaoFeignClient.readPlacesByKeywordInRadius(
			KAKAO_REQUEST_HEADER, keyword, latitude, longitude, PLACE_RADIUS, page);
	}

	/**
	 * keyword 의 사진 데이터를 읽어 옵니다.
	 */
	public ImageNaverModel readImageModel(String keyword) {
		final int DATA_COUNT = 1;
		final String SORT_TYPE = "sim";

		ImageNaverModel imageModel = naverFeignClient.readImageModelByPlaceName(
			NAVER_ID, NAVER_SECRET, keyword, DATA_COUNT, SORT_TYPE
		);

		return imageModel;
	}

}
