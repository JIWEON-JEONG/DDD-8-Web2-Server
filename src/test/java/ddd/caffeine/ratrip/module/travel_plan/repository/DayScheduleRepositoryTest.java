package ddd.caffeine.ratrip.module.travel_plan.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import ddd.caffeine.ratrip.TestConfig;
import ddd.caffeine.ratrip.module.travel_plan.model.DaySchedule;

@DataJpaTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DayScheduleRepositoryTest {

	@Autowired
	DayScheduleRepository dayScheduleRepository;

	@Test
	@DisplayName("saveAll DB connection 1번 정상 작동 확인")
	public void saveAllTest() {
		//given
		List<DaySchedule> daySchedules = List.of(
			DaySchedule.builder().build(),
			DaySchedule.builder().build(),
			DaySchedule.builder().build()
		);

		//when
		dayScheduleRepository.saveAll(daySchedules);
		List<DaySchedule> responses = dayScheduleRepository.findAll();

		//then
		assertThat(responses.size()).isEqualTo(3);
	}
}