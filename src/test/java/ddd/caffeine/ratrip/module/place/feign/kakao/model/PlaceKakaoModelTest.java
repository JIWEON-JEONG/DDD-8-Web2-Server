package ddd.caffeine.ratrip.module.place.feign.kakao.model;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ddd.caffeine.ratrip.common.exception.domain.PlaceException;

class PlaceKakaoModelTest {

	@Test
	@DisplayName("데이터가 없을 경우 메서드 예외 정상 동작 테스트")
	void readOneTest() {
		//given
		PlaceKakaoModel placeKakaoModel = new PlaceKakaoModel();
		placeKakaoModel.documents = new ArrayList<>();

		//then
		assertThatThrownBy(() -> placeKakaoModel.readOne())
			.isInstanceOf(PlaceException.class)
			.hasMessage("존재하지 않는 장소입니다.");
	}
}