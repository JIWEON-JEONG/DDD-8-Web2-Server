package ddd.caffeine.ratrip.module.place;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ddd.caffeine.ratrip.common.exception.domain.FeignException;
import ddd.caffeine.ratrip.common.exception.domain.PlaceException;
import ddd.caffeine.ratrip.module.place.service.PlaceValidator;

class PlaceValidatorTest {

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
			.isInstanceOf(FeignException.class)
			.hasMessage("Page 는 1 이상 45 이하 여야 합니다.");
	}

	@ParameterizedTest
	@DisplayName("위도 범위 유효성 검사 테스트")
	@ValueSource(strings = {"100", "-90.01", "90.93"})
	void validateLatitudeSize(String latitude) {
		//then
		assertThatThrownBy(() ->
			recommendPlaceValidator.validateRangeLatitude(latitude))
			.isInstanceOf(PlaceException.class)
			.hasMessage("위도는 -90 ~ 90 까지 범위안에 존재해야합니다.");
	}

	@ParameterizedTest
	@DisplayName("경도 범위 유효성 검사 테스트")
	@ValueSource(strings = {"190", "-180.01", "180.93"})
	void validateLongitudeSize(String longitude) {
		//then
		assertThatThrownBy(() ->
			recommendPlaceValidator.validateRangeLongitude(longitude))
			.isInstanceOf(PlaceException.class)
			.hasMessage("경도는 -180 ~ 180 까지 범위안에 존재해야합니다.");
	}

	@ParameterizedTest
	@DisplayName("좌표 유효성 검사 테스트")
	@ValueSource(strings = {"test", "3..321341.", "한국"})
	void validateCoordinate(String coordinate) {
		//then
		assertThatThrownBy(() ->
			recommendPlaceValidator.validateRangeLatitude(coordinate))
			.isInstanceOf(PlaceException.class)
			.hasMessage("좌표는 숫자여야합니다.");
	}
}