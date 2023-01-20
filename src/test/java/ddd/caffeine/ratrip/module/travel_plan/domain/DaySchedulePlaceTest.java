package ddd.caffeine.ratrip.module.travel_plan.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DaySchedulePlaceTest {

	@Test
	@DisplayName("exchangeOrder 메서드 정상 동작 테스트")
	void exchangeOrderTest() {
		//given
		DaySchedulePlace baseDaySchedulePlace = DaySchedulePlace.builder()
			.order(1)
			.build();
		DaySchedulePlace exchangeDaySchedulePlace = DaySchedulePlace.builder()
			.order(2)
			.build();

		//when
		baseDaySchedulePlace.exchangeOrder(exchangeDaySchedulePlace);

		//then
		assertThat(baseDaySchedulePlace.getOrder()).isEqualTo(2);
		assertThat(exchangeDaySchedulePlace.getOrder()).isEqualTo(1);
	}
}