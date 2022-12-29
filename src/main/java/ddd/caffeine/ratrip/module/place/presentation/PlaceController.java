package ddd.caffeine.ratrip.module.place.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.module.place.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * 장소 추천 API
 */
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/place")
public class PlaceController {
	private final PlaceService placeService;

	// @GetMapping(value = "search")
	// public ResponseEntity<SearchPlaceResponseDto> callRecommendPlaceApi(
	// 	@RequestParam String keyword,
	// 	@RequestParam String latitude,
	// 	@RequestParam String longitude,
	// 	@RequestParam(required = false, defaultValue = "1") int page) {
	//
	// 	placeService.searchPlaces(keyword, latitude, longitude, page);
	// 	SearchPlaceResponseDto response = new SearchPlaceResponseDto();
	// 	return ResponseEntity.ok(response);
	// }
}
