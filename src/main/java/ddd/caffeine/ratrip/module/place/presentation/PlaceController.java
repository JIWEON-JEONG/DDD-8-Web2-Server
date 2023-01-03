package ddd.caffeine.ratrip.module.place.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.module.place.PlaceService;
import ddd.caffeine.ratrip.module.place.presentation.dto.PlaceSearchResponseDto;
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

	@GetMapping(value = "search")
	public ResponseEntity<PlaceSearchResponseDto> callPlaceSearchApi(
		@RequestParam String keyword,
		@RequestParam String latitude,
		@RequestParam String longitude,
		@RequestParam(required = false, defaultValue = "1") int page) {

		PlaceSearchResponseDto response = placeService.searchPlaces(keyword, latitude, longitude, page);

		return ResponseEntity.ok(response);
	}
}
