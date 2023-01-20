package ddd.caffeine.ratrip.module.travel_plan.application;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ddd.caffeine.ratrip.module.place.domain.Place;
import ddd.caffeine.ratrip.module.travel_plan.domain.DaySchedulePlace;
import ddd.caffeine.ratrip.module.travel_plan.domain.repository.dao.DaySchedulePlaceDao;
import ddd.caffeine.ratrip.module.travel_plan.domain.repository.day_schedule.DaySchedulePlaceRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DaySchedulePlaceService {

	private final DaySchedulePlaceRepository daySchedulePlaceRepository;

	public List<DaySchedulePlaceDao> readDaySchedulePlaces(UUID dayScheduleUUID) {
		return daySchedulePlaceRepository.findDaySchedulePlaceDaoByDayScheduleUUID(dayScheduleUUID);
	}

	public void addPlace(UUID dayScheduleUUID, Place place, String memo) {
		// DaySchedulePlace daySchedulePlace = new DaySchedulePlace()
		// daySchedulePlaceRepository.save()
	}

	public void exchangePlaceSequence(UUID dayScheduleUUID, List<UUID> placeUUIDs) {
		List<DaySchedulePlace> daySchedulePlaces = daySchedulePlaceRepository.findByDayScheduleUUIDAndPlaceUUIDs(
			dayScheduleUUID, placeUUIDs.get(0), placeUUIDs.get(1));

		DaySchedulePlace baseDaySchedulePlace = daySchedulePlaces.get(0);
		baseDaySchedulePlace.exchangeOrder(daySchedulePlaces.get(1));
	}
}
