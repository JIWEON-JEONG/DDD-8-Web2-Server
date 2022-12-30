package ddd.caffeine.ratrip.module.place.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ddd.caffeine.ratrip.module.feign.domain.place.kakao.KakaoFeignClient;
import ddd.caffeine.ratrip.module.feign.domain.place.kakao.PlaceKakaoModel;
import ddd.caffeine.ratrip.module.feign.domain.place.naver.ImageNaverModel;
import ddd.caffeine.ratrip.module.feign.domain.place.naver.NaverFeignClient;
import ddd.caffeine.ratrip.module.place.model.Place;
import ddd.caffeine.ratrip.module.place.presentation.dto.PlaceDetailsResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.PlaceSearchResponseDto;
import ddd.caffeine.ratrip.module.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;

/**
 * 1. 장소를 검색 하여, 있다면 데이터 내려주고, 없다면 api 호출.
 *
 * 예외
 * - 장소가 업데이트 되었을 경우. (이사 갔거나 뿌셨거나)
 * - 장소가 업데이트 되었는데 사진이 없을경우 -> 프론트에 기본적으로 사진 못찾음 이미지 띄워주게 해야 할 듯.
 *
 */
@Service
@RequiredArgsConstructor
public class PlaceService {

	@Value("${KAKAO_API_KEY}")
	private String KAKAO_API_KEY;
	final String KAKAO_REQUEST_HEADER = "KakaoAK " + KAKAO_API_KEY;

	@Value("${NAVER_ID}")
	private String NAVER_ID;
	@Value("${NAVER_SECRET}")
	private String NAVER_SECRET;

	private final KakaoFeignClient kakaoFeignClient;
	private final NaverFeignClient naverFeignClient;
	private final PlaceValidator placeValidator;
	private final PlaceRepository placeRepository;

	public PlaceSearchResponseDto searchPlaces(String keyword, String latitude, String longitude, int page) {
		validateSearchRequestParameters(latitude, longitude, page);
		PlaceKakaoModel placeKakaoModel = readPlaces(keyword, latitude, longitude, page);

		return placeKakaoModel.mapByPlaceSearchResponseDto();
	}

	public PlaceDetailsResponseDto readPlaceDetails(String kakaoId, String address, String placeName) {
		Optional<Place> optionalPlace = placeRepository.findByKakaoId(kakaoId);
		if (optionalPlace.isEmpty()) {
			Place place = readPlaceEntity(address, placeName);
			placeRepository.save(place);
			return new PlaceDetailsResponseDto(place);
		}

		Place place = optionalPlace.get();
		handlePlaceUpdate(place, address, placeName);
		return new PlaceDetailsResponseDto(place);
	}

	private void handlePlaceUpdate(Place place, String address, String placeName) {
		if (place.checkNeedsUpdate(address, placeName)) {
			PlaceKakaoModel placeKakaoModel = readOnePlace(address, placeName);
			place.update(placeKakaoModel.readPlaceDataIndexZero());
		}
	}

	private Place readPlaceEntity(String address, String placeName) {
		PlaceKakaoModel placeKakaoModel = readOnePlace(address, placeName);
		Place place = placeKakaoModel.mapByPlaceEntity();

		place.injectImageLink(readImageModel(placeName));

		return place;
	}

	private PlaceKakaoModel readOnePlace(String address, String placeName) {
		String keyword = address + " " + placeName;
		final int DATA_COUNT = 1;

		return kakaoFeignClient.readPlaceByKeyword(KAKAO_REQUEST_HEADER, keyword,
			DATA_COUNT);
	}

	/**
	 * @Todo : 반경 몇 km 까지 할 것인지 프론트 개발자님들과 상의.
	 */
	private PlaceKakaoModel readPlaces(String keyword, String latitude, String longitude, int page) {
		final int PLACE_RADIUS = 5000;

		return kakaoFeignClient.readPlacesByKeywordInRadius(
			KAKAO_REQUEST_HEADER, keyword, latitude, longitude, PLACE_RADIUS, page);
	}

	private void validateSearchRequestParameters(String latitude, String longitude, int page) {
		placeValidator.validateRangeLatitude(latitude);
		placeValidator.validateRangeLongitude(longitude);
		placeValidator.validatePageSize(page);
	}

	private String readImageModel(String keyword) {
		final int DATA_COUNT = 1;
		final String SORT_TYPE = "sim";
		final int DATA_INDEX = 0;

		ImageNaverModel imageModel = naverFeignClient.readImageModelByPlaceName(
			NAVER_ID, NAVER_SECRET, keyword, DATA_COUNT, SORT_TYPE
		);

		return imageModel.readImageLinkByIndex(DATA_INDEX);
	}
}
