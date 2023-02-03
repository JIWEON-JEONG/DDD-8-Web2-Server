package ddd.caffeine.ratrip.module.travel_plan.application.day_schedule;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import ddd.caffeine.ratrip.module.travel_plan.domain.day_schedule.DaySchedulePlace;
import ddd.caffeine.ratrip.module.travel_plan.domain.day_schedule.repository.DaySchedulePlaceRepository;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DaySchedulePlaceServiceTest {

	@Mock
	DaySchedulePlaceRepository daySchedulePlaceRepository;
	DaySchedulePlaceService daySchedulePlaceService;

	@BeforeAll
	void init() {
		daySchedulePlaceService = new DaySchedulePlaceService(
			new DaySchedulePlaceValidator(), daySchedulePlaceRepository
		);
	}

	@Test
	@DisplayName("일정 내의 장소 순서 업데이트 정상 동작 테스트")
	void updatePlacesSequenceTest() {
		//given
		UUID dayScheduleUUID = UUID.fromString("11111111-1111-1111-1111-111111111111");
		List<UUID> daySchedulePlaceUUIDs = List.of(
			UUID.fromString("11111111-1111-1111-1111-111111111111"),
			UUID.fromString("22222222-2222-2222-2222-222222222222")
		);

		List<DaySchedulePlace> daySchedulePlaces = readStubDaySchedulePlaces();

		for (DaySchedulePlace daySchedulePlace : daySchedulePlaces) {
			System.out.println(daySchedulePlace.getId());
		}

		// //when
		// when(daySchedulePlaceRepository.findDaySchedulePlacesByDayScheduleUUID(dayScheduleUUID))
		// 	.thenReturn(testLecture);
		//
		// //then
		// assertThat(lec)).isEqualTo(testLecture);
	}

	private List<DaySchedulePlace> readStubDaySchedulePlaces() {
		DaySchedulePlace sequence1 = DaySchedulePlace.builder().sequence(1).build();
		DaySchedulePlace sequence2 = DaySchedulePlace.builder().sequence(2).build();

		ReflectionTestUtils.setField(sequence1, "id",
			UUID.fromString("11111111-1111-1111-1111-111111111111"));
		ReflectionTestUtils.setField(sequence2, "id",
			UUID.fromString("22222222-2222-2222-2222-222222222222"));

		List<DaySchedulePlace> daySchedulePlaces = List.of(
			sequence1, sequence2
		);

		return daySchedulePlaces;
	}

	// //given
	// Pageable pageable = PageRequest.of(0, 1, Sort.by("modifiedDate").descending());
	// Page<Lecture> lectureContentNull = new PageImpl<>(new ArrayList<>(), pageable, 0);
	// LectureToJsonArray result = new LectureToJsonArray(new ArrayList<>(), 0l);
	//
	// //when
	// when(lectureRepository.searchLectureForLectureName("null", null, pageable, "null"))
	// 	.thenReturn(lectureContentNull);
	//
	// //
	// assertThat(lectureService.searchLectureForLectureName("null", Optional.empty(),
	// pageable, Optional.of("null")).getData()).isEqualTo(new ArrayList<>());
	// assertThat(lectureService.searchLectureForLectureName("null", Optional.empty(),
	// pageable, Optional.of("null")).getCount()).isEqualTo(0);
}