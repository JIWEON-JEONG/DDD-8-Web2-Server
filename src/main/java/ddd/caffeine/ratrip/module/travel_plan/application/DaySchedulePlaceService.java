package ddd.caffeine.ratrip.module.travel_plan.application;

import org.springframework.stereotype.Service;

import ddd.caffeine.ratrip.module.travel_plan.domain.repository.DaySchedulePlaceRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DaySchedulePlaceService {

	private final DaySchedulePlaceRepository daySchedulePlaceRepository;
}
