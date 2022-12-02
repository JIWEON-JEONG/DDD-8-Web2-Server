package ddd.caffeine.ratrip.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.RecommendPlaceUseCase;
import ddd.caffeine.ratrip.controller.dto.RecommendResponseDto;
import lombok.RequiredArgsConstructor;

/**
 * 주변 장소 추천 API
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommend")
public class RecommendPlaceController {
	private final RecommendPlaceUseCase recommendPlaceUseCase;

	@GetMapping(value = "place/{latitude}/{")
	public ResponseEntity<RecommendResponseDto> callRecommendPlaceApi(@RequestParam(name = "region") String region,
		@RequestParam(name = "category") String placeCategory
	) {
		RecommendResponseDto response = recommendPlaceUseCase.recommendPlace(region, placeCategory);
		return ResponseEntity.ok(response);
	}
}
