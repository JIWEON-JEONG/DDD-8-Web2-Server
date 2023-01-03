package ddd.caffeine.ratrip.module.place.service;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import ddd.caffeine.ratrip.common.exception.domain.FeignException;
import ddd.caffeine.ratrip.common.exception.domain.PlaceException;
import lombok.extern.slf4j.Slf4j;

/**
 * 유효성 검증 클래스.
 */
@Slf4j
@Component
public class PlaceValidator {

	public void validateLotNumberAddress(String address) {
		final Pattern LOT_NUMBER_PATTERN = Pattern.compile(
			"(([가-힣A-Za-z·\\d~\\-\\.]+(읍|동)\\s)[\\d-]+)|(([가-힣A-Za-z·\\d~\\-\\.]+(읍|동)\\s)\\d[^시]+)");
		if (!(LOT_NUMBER_PATTERN.matcher(extractAddressKeyword(address)).matches())) {
			throw new PlaceException(INVALID_ADDRESS_EXCEPTION);
		}
	}

	public void validateRoadNameAddress(String address) {
		final Pattern ROAD_NAME_ADDRESS = Pattern.compile(
			"(([가-힣A-Za-z·\\d~\\-\\.]{2,}(로|길).\\d+)|([가-힣A-Za-z·\\d~\\-\\.]+(읍|동)\\s)\\d+)");
		if (!(ROAD_NAME_ADDRESS.matcher(extractAddressKeyword(address)).matches())) {
			throw new PlaceException(INVALID_ADDRESS_EXCEPTION);
		}
	}

	public void validateIsNumber(String number) {
		try {
			Long.parseLong(number);
		} catch (NumberFormatException e) {
			throw new PlaceException(INVALID_THIRD_PARTY_ID_EXCEPTION);
		}
	}

	public void validatePageSize(int page) {
		final int MIN_PAGE = 1;
		final int MAX_PAGE = 45;
		if (page < MIN_PAGE || page > MAX_PAGE) {
			throw new FeignException(KAKAO_PAGE_NUMBER_EXCEPTION);
		}
	}

	public void validateRangeLatitude(String lat) {
		double latitude = validateTypeCastDouble(lat);
		if (!(-90 <= latitude && latitude <= 90)) {
			throw new PlaceException(INVALID_LATITUDE_RANGE_EXCEPTION);
		}
	}

	public void validateRangeLongitude(String lng) {
		double longitude = validateTypeCastDouble(lng);
		if (!(-180 <= longitude && longitude <= 180)) {
			throw new PlaceException(INVALID_LONGITUDE_RANGE_EXCEPTION);
		}
	}

	private double validateTypeCastDouble(String param) {
		try {
			return Double.parseDouble(param);
		} catch (NumberFormatException e) {
			throw new PlaceException(INVALID_COORDINATE_EXCEPTION);
		}
	}

	/**
	 * EX) 경기 광주시 남한산성면 검복길 82 -> 검복길 82
	 */
	private String extractAddressKeyword(String address) {
		String[] keywords = address.split(" ");
		return keywords[keywords.length - 2] + keywords[keywords.length - 1];
	}
}

