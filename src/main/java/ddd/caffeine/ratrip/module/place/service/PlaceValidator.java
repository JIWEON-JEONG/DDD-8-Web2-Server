package ddd.caffeine.ratrip.module.place.service;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import ddd.caffeine.ratrip.common.exception.domain.FeignException;
import ddd.caffeine.ratrip.common.exception.domain.PlaceException;
import ddd.caffeine.ratrip.module.place.model.Place;
import ddd.caffeine.ratrip.module.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 유효성 검증 클래스.
 */
@Slf4j
@Component
@RequiredArgsConstructor //TODO - 지원이랑 얘기
public class PlaceValidator {
	private final PlaceRepository placeRepository;

	public void validateLotNumberAddress(String address) {
		final Pattern LOT_NUMBER_ADDRESS = Pattern.compile("(.+[가-힣A-Za-z·\\d\\-\\.]{2,}(읍|면|동|리).[\\d\\-]+)");

		if (!(LOT_NUMBER_ADDRESS.matcher(address).matches())) {
			throw new PlaceException(INVALID_ADDRESS_EXCEPTION);
		}
	}

	public void validateRoadNameAddress(String address) {
		final Pattern ROAD_NAME_ADDRESS = Pattern.compile("(.+[가-힣A-Za-z·\\d\\-\\.]{2,}(로|길).\\d+)");

		if (!(ROAD_NAME_ADDRESS.matcher(address).matches())) {
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

	public void validateUUIDForm(String uuid) {
		final Pattern UUID_PATTERN = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
		if (!(UUID_PATTERN.matcher(uuid).matches())) {
			throw new PlaceException(INVALID_UUID_FORM_EXCEPTION);
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

	public Place validateIsExistAndGetPlace(UUID placeId) {
		return placeRepository.findById(placeId)
			.orElseThrow(() -> new PlaceException(NOT_FOUND_PLACE_EXCEPTION));
	}

	private double validateTypeCastDouble(String param) {
		try {
			return Double.parseDouble(param);
		} catch (NumberFormatException e) {
			throw new PlaceException(INVALID_COORDINATE_EXCEPTION);
		}
	}
}

