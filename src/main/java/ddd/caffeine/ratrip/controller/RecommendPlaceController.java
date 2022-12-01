package ddd.caffeine.ratrip.controller;

import ddd.caffeine.ratrip.RecommendPlaceUseCase;
import ddd.caffeine.ratrip.controller.dto.RecommendResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 주변 장소 추천 API
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommend")
public class RecommendPlaceController {
    private final RecommendPlaceUseCase recommendPlaceUseCase;

    @GetMapping(value = "place/{latitude}/{")
    public ResponseEntity<RecommendResponseDto> callRecommendPlaceApi(
            @RequestParam(name = "x") String longitude,
            @RequestParam(name = "y") String latitude,
            @RequestParam(name = "category") String placeCategory
    ) {
        RecommendResponseDto response = recommendPlaceUseCase.recommendPlace(longitude, latitude, placeCategory);
        return ResponseEntity.ok(response);
    }
}
