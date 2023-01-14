package ddd.caffeine.ratrip.module.place.presentation;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.module.place.presentation.dto.PlaceDetailsResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.PlaceInRegionResponseDto;
import ddd.caffeine.ratrip.module.place.presentation.dto.PlaceSearchResponseDto;
import ddd.caffeine.ratrip.module.place.service.PlaceService;
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

	@GetMapping("search")
	public ResponseEntity<PlaceSearchResponseDto> callPlaceSearchApi(
		@RequestParam String keyword,
		@RequestParam String latitude,
		@RequestParam String longitude,
		@RequestParam(required = false, defaultValue = "1") int page) {

		PlaceSearchResponseDto response = placeService.searchPlaces(
			keyword, latitude, longitude, page);

		return ResponseEntity.ok(response);
	}

	@GetMapping("third-party-id")
	public ResponseEntity<PlaceDetailsResponseDto> callPlaceDetailsApiByThirdPartyId(
		@RequestParam String id,
		@RequestParam String placeName,
		@RequestParam String address) {

		PlaceDetailsResponseDto response = placeService.readPlaceDetailsByThirdPartyId(
			id, address, placeName);

		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<PlaceDetailsResponseDto> callPlaceDetailsApiByUUID(
		@RequestParam String placeId) {

		PlaceDetailsResponseDto response = placeService.readPlaceDetailsByUUID(placeId);
		return ResponseEntity.ok(response);
	}

	/**
	 * Todo : offset 정하기.
	 * ex) v1/place/regions/region=XX&region=XX&
	 */
	@GetMapping(value = "regions")
	public ResponseEntity<PlaceInRegionResponseDto> callPlacesInRegionsApi(
		@RequestParam(name = "region", required = false, defaultValue = "전국") List<String> regions,
		@PageableDefault(page = 1, sort = "popular", direction = Sort.Direction.DESC) Pageable pageable) {
		PlaceInRegionResponseDto response = placeService.readPlacesInRegionsApi(regions, pageable);
		return ResponseEntity.ok(response);
	}
}
