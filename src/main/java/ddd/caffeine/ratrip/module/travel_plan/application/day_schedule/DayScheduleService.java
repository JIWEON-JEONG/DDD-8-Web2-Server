package ddd.caffeine.ratrip.module.travel_plan.application.day_schedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ddd.caffeine.ratrip.module.place.domain.Place;
import ddd.caffeine.ratrip.module.travel_plan.domain.TravelPlan;
import ddd.caffeine.ratrip.module.travel_plan.domain.day_schedule.DaySchedule;
import ddd.caffeine.ratrip.module.travel_plan.domain.day_schedule.repository.DayScheduleRepository;
import ddd.caffeine.ratrip.module.travel_plan.domain.day_schedule.repository.dao.DaySchedulePlaceDao;
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

	public UUID addPlace(UUID dayScheduleUUID, Place place, String memo) {
		Optional<DaySchedule> optionalDaySchedule = dayScheduleRepository.findById(dayScheduleUUID);
		DaySchedule daySchedule = dayScheduleValidator.validateExistDaySchedule(optionalDaySchedule);
		return daySchedulePlaceService.addPlace(daySchedule, place, memo);
	}

	public DayScheduleResponseDto readDaySchedule(UUID dayScheduleUUID) {
		Optional<DaySchedule> daySchedule = dayScheduleRepository.findById(dayScheduleUUID);
		dayScheduleValidator.validateExistDaySchedule(daySchedule);

		List<DaySchedulePlaceDao> daySchedulePlaces = daySchedulePlaceService.readDaySchedulePlaces(
			daySchedule.get().getId());

		return DayScheduleResponseDto.builder()
			.dayScheduleUUID(daySchedule.get().getId())
			.daySchedulePlaces(createDaySchedulePlaceDto(daySchedulePlaces))
			.hasRegisteredPlace(!(daySchedulePlaces.isEmpty()))
			.build();
	}

	public List<DaySchedule> readDaySchedulesInTravelPlan(UUID travelPlanUUID) {
		return dayScheduleRepository.findByTravelPlanId(travelPlanUUID);
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
