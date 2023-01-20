package ddd.caffeine.ratrip.module.travel_plan.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ddd.caffeine.ratrip.module.place.domain.Place;
import ddd.caffeine.ratrip.module.travel_plan.domain.DaySchedule;
import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlan;
import ddd.caffeine.ratrip.module.travel_plan.domain.repository.dao.DaySchedulePlaceDao;
import ddd.caffeine.ratrip.module.travel_plan.domain.repository.day_schedule.DayScheduleRepository;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule.DaySchedulePlaceDto;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule.DayScheduleResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DayScheduleService {

	private final DaySchedulePlaceService daySchedulePlaceService;
	private final DayScheduleValidator dayScheduleValidator;
	private final DayScheduleRepository dayScheduleRepository;

	public void initTravelPlan(TravelPlan travelPlan, List<LocalDate> dates) {
		List<DaySchedule> daySchedules = new ArrayList<>();
		for (LocalDate date : dates) {
			DaySchedule daySchedule = DaySchedule.builder()
				.travelPlan(travelPlan)
				.date(date)
				.build();
			daySchedules.add(daySchedule);
		}
		dayScheduleRepository.saveAll(daySchedules);
	}

	public void addPlace(UUID dayScheduleUUID, Place place, String memo) {
		Optional<DaySchedule> optionalDaySchedule = dayScheduleRepository.findById(dayScheduleUUID);
		DaySchedule daySchedule = dayScheduleValidator.validateExistDaySchedule(optionalDaySchedule);
		daySchedulePlaceService.addPlace(daySchedule, place, memo);
	}

	public DayScheduleResponseDto readDaySchedule(UUID travelPlanUUID, LocalDate date) {
		DaySchedule daySchedule = dayScheduleRepository.findByTravelPlanIdAndDate(travelPlanUUID, date);

		List<DaySchedulePlaceDao> daySchedulePlaces = daySchedulePlaceService.readDaySchedulePlaces(
			daySchedule.getId());

		return DayScheduleResponseDto.builder()
			.dayScheduleUUID(daySchedule.getId())
			.daySchedulePlaces(createDaySchedulePlaceDto(daySchedulePlaces))
			.hasRegisteredPlace(!(daySchedulePlaces.isEmpty()))
			.build();
	}

	public void exchangePlaceSequence(UUID dayScheduleUUID, List<UUID> placeUUIDs) {
		daySchedulePlaceService.exchangePlaceSequence(dayScheduleUUID, placeUUIDs);
	}

	private List<DaySchedulePlaceDto> createDaySchedulePlaceDto(List<DaySchedulePlaceDao> daySchedulePlaceDaos) {
		List<DaySchedulePlaceDto> response = new ArrayList<>();
		for (DaySchedulePlaceDao dao : daySchedulePlaceDaos) {
			response.add(new DaySchedulePlaceDto(dao));
		}
		return response;
	}
}
