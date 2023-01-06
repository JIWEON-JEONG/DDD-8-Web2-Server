package ddd.caffeine.ratrip.module.feign.domain.place.kakao.model;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlaceKakaoModelTest {

	@Test
	@DisplayName("데이터가 없을 경우 메서드 정상 동작 테스트")
	void readOneTest() {
		//given
		PlaceKakaoModel placeKakaoModel = new PlaceKakaoModel();
		placeKakaoModel.documents = new ArrayList<>();

		//when
		placeKakaoModel.readOne();
	}
}