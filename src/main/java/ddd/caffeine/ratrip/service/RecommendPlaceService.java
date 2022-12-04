package ddd.caffeine.ratrip.service;

import org.springframework.stereotype.Service;

import ddd.caffeine.ratrip.RecommendPlaceUseCase;
import ddd.caffeine.ratrip.controller.dto.RecommendResponseDto;

@Service
public class RecommendPlaceService implements RecommendPlaceUseCase {
	@Override
	public RecommendResponseDto recommendPlace(String region, String placeCategory) {

	}
}
