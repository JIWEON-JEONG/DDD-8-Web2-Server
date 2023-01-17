package ddd.caffeine.ratrip.module.travel_plan;

import org.springframework.stereotype.Service;

import ddd.caffeine.ratrip.module.travel_plan.repository.DaySchedulePlaceRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DaySchedulePlaceService {

	private final DaySchedulePlaceRepository daySchedulePlaceRepository;
}
