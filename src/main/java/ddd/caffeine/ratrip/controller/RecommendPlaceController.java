package ddd.caffeine.ratrip.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.service.RecommendPlaceUseCase;
import ddd.caffeine.ratrip.service.feign.model.KakaoFeignResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * 장소 추천 API
 */
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/recommend")
public class RecommendPlaceController {
	private final RecommendPlaceUseCase recommendPlaceUseCase;

	@GetMapping(value = "place")
	public ResponseEntity<KakaoFeignResponseDto> callRecommendPlaceApi(@RequestParam String region,
		@RequestParam String keyword, @RequestParam(required = false, defaultValue = "1") int page
	) {
		KakaoFeignResponseDto response = recommendPlaceUseCase.recommendPlace(region, keyword, page);
		return ResponseEntity.ok(response);
	}
}
