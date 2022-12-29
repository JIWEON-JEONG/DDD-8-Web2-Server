package ddd.caffeine.ratrip.module.place;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import org.springframework.stereotype.Component;

import ddd.caffeine.ratrip.common.exception.domain.KakaoFeignException;
import ddd.caffeine.ratrip.common.exception.domain.PlaceException;
import lombok.extern.slf4j.Slf4j;

/**
 * 유효성 검증 클래스.
 */
@Slf4j
@Component
public class PlaceValidator {
	public void validatePageSize(int page) {
		final int MIN_PAGE = 1;
		final int MAX_PAGE = 45;
		if (page < MIN_PAGE || page > MAX_PAGE) {
			throw new KakaoFeignException(KAKAO_PAGE_NUMBER_EXCEPTION);
		}
	}

	public void validateRangeLatitude(String lat) {
		double latitude = typeCastStringToDouble(lat);
		if (!(-90 <= latitude && latitude <= 90)) {
			throw new PlaceException(INVALID_LATITUDE_RANGE_EXCEPTION);
		}
	}

	public void validateRangeLongitude(String lng) {
		double longitude = typeCastStringToDouble(lng);
		if (!(-180 <= longitude && longitude <= 180)) {
			throw new PlaceException(INVALID_LONGITUDE_RANGE_EXCEPTION);
		}
	}

	private double typeCastStringToDouble(String param) {
		try {
			return Double.parseDouble(param);
		} catch (NumberFormatException e) {
			throw new PlaceException(INVALID_COORDINATE_EXCEPTION);
		}
	}
}

