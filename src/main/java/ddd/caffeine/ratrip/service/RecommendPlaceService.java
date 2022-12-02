package ddd.caffeine.ratrip.service;

import ddd.caffeine.ratrip.RecommendPlaceUseCase;
import ddd.caffeine.ratrip.controller.dto.RecommendResponseDto;
import org.springframework.stereotype.Service;

@Service
public class RecommendPlaceService implements RecommendPlaceUseCase {
    @Override
    public RecommendResponseDto recommendPlace(String region, String placeCategory) {

    }
}
