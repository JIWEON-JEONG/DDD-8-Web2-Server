package ddd.caffeine.ratrip.module.place.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlaceTest {

	@BeforeEach
	void init() {
	}

	@Test
	@DisplayName("setPlaceCategory 메서드 정상 동작 테스트")
	void setPlaceCategoryTest() {
		//given
		String cafeCategoryCode = "CE7";
		String 기타CategoryCode = "관리 하지 않는 카테고리 코드";
		Place placeCafe = new Place();
		Place placeNoCategory = new Place();

		//when
		placeCafe.setPlaceCategory(cafeCategoryCode);
		placeNoCategory.setPlaceCategory(기타CategoryCode);

		//then
		Assertions.assertThat(placeCafe.getCategory()).isEqualTo(Category.CAFE);
		Assertions.assertThat(placeNoCategory.getCategory()).isEqualTo(Category.기타);
	}

	@Test
	@DisplayName("setAddress 메서드 정상 동작 테스트")
	void setAddressTest() {
		//given
		String 제주Address = "제주특별자치도 제주시 외도일동 640-2";
		String 기타Address = "XX도 제주시 외도일동 640-2";
		Place 제주Place = new Place();
		Place 기타Place = new Place();

		//when
		제주Place.setAddress(제주Address);
		기타Place.setAddress(기타Address);

		//then
		Address 제주 = 제주Place.getAddress();
		Address 기타 = 기타Place.getAddress();
		Assertions.assertThat(제주.getRegion()).isEqualTo(Region.제주특별자치도);
		Assertions.assertThat(제주.getDetailed()).isEqualTo("제주시 외도일동 640-2");
		Assertions.assertThat(기타.getRegion()).isEqualTo(Region.기타);
	}
}