package ddd.caffeine.ratrip.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.RecommendPlaceUseCase;
import ddd.caffeine.ratrip.service.KakaoFeignResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * 장소 추천 API
 */
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("api/v1/recommend")
public class RecommendPlaceController {
	private final RecommendPlaceUseCase recommendPlaceUseCase;

	@GetMapping(value = "place")
	public ResponseEntity<KakaoFeignResponseDto> callRecommendPlaceApi(@RequestParam(name = "region") String region,
		@RequestParam(name = "keyword") String keyword
	) {
		KakaoFeignResponseDto response = recommendPlaceUseCase.recommendPlace(region, keyword);
		return ResponseEntity.ok(response);
	}
}
