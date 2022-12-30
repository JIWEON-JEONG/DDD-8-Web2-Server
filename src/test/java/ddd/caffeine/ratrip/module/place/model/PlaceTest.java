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
	@DisplayName("injectCategory 메서드 정상 동작 테스트")
	void injectCategoryTest() {
		//given
		String cafeCategoryCode = "CE7";
		String 기타CategoryCode = "관리 하지 않는 카테고리 코드";

		//when
		Place placeCafe = new Place();
		Place placeNoCategory = new Place();
		placeCafe.injectPlaceCategory(cafeCategoryCode);
		placeNoCategory.injectPlaceCategory(기타CategoryCode);

		//then
		Assertions.assertThat(placeCafe.getCategory()).isEqualTo(Category.CAFE);
		Assertions.assertThat(placeNoCategory.getCategory()).isEqualTo(Category.기타);
	}

}