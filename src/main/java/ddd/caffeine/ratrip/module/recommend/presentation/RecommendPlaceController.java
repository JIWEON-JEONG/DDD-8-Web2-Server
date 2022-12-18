package ddd.caffeine.ratrip.module.recommend.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.module.recommend.presentation.dto.RecommendResponseDto;
import ddd.caffeine.ratrip.module.recommend.service.RecommendPlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * 장소 추천 API
 */
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/recommend")
public class RecommendPlaceController {
	private final RecommendPlaceService recommendPlaceService;

	@GetMapping(value = "place")
	public ResponseEntity<RecommendResponseDto> callRecommendPlaceApi(
		@RequestParam String region,
		@RequestParam String keyword,
		@RequestParam(required = false, defaultValue = "1") int page) {
		RecommendResponseDto response = new RecommendResponseDto(
			recommendPlaceService.recommendPlaces(region, keyword, page));
		return ResponseEntity.ok(response);
	}
}
