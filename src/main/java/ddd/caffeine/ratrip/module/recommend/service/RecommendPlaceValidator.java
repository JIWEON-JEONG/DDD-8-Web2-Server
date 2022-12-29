package ddd.caffeine.ratrip.module.recommend.service;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import org.springframework.stereotype.Component;

import ddd.caffeine.ratrip.common.exception.domain.FeignException;
import lombok.extern.slf4j.Slf4j;

/**
 * 유효성 검증 클래스.
 */
@Slf4j
@Component
public class RecommendPlaceValidator {
	public void validatePageSize(int page) {
		final int MIN_PAGE = 1;
		final int MAX_PAGE = 45;
		if (page < MIN_PAGE || page > MAX_PAGE) {
			throw new FeignException(KAKAO_PAGE_NUMBER_EXCEPTION);
		}
	}
}

