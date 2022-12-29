package ddd.caffeine.ratrip.service;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ddd.caffeine.ratrip.common.exception.domain.KakaoFeignException;
import ddd.caffeine.ratrip.module.place.PlaceValidator;

/**
 * RecommendPlaceService 의 단위테스트 구현
 */
class RecommendPlaceValidatorTest {

	PlaceValidator recommendPlaceValidator;

	@BeforeEach
	void init() {
		recommendPlaceValidator = new PlaceValidator();
	}

	@ParameterizedTest
	@DisplayName("페이징 사이즈 유효성 검사 테스트")
	@ValueSource(ints = {-1, 0, 46, 1000000})
	void validatePageSizeTest(int pageSize) {
		//then
		assertThatThrownBy(() ->
			recommendPlaceValidator.validatePageSize(pageSize))
			.isInstanceOf(KakaoFeignException.class)
			.hasMessage("Page 는 1 이상 45 이하 여야 합니다.");
	}
}