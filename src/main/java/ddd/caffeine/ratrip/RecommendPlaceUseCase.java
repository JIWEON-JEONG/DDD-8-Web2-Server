package ddd.caffeine.ratrip;

import ddd.caffeine.ratrip.controller.dto.RecommendRequestDto;
import ddd.caffeine.ratrip.controller.dto.RecommendResponseDto;

public interface RecommendPlaceUseCase {
    RecommendResponseDto recommendPlace(String longitude, String latitude, String placeCategory);
}
