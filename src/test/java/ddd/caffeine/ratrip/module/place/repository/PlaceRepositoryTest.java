package ddd.caffeine.ratrip.module.place.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import ddd.caffeine.ratrip.TestConfig;
import ddd.caffeine.ratrip.module.place.model.Place;

@DataJpaTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PlaceRepositoryTest {

	@Autowired
	PlaceRepository placeRepository;

	@Test
	@DisplayName("findByKakaoId 정상 동작 테스트")
	void findByKakaoIdTest() {
		//given
		Place testPlace = createPlace("testId", "testName", "testAddress", "testCategoryCode", 120.365, 34.678,
			"testLink",
			"testPhoneNumber");
		placeRepository.save(testPlace);

		//when
		Optional<Place> place = placeRepository.findByKakaoId("testId");

		//then
		assertThat(place).isPresent();
		assertThat(place.get().getId()).isNotNull();
		assertThat(place.get().getName()).isEqualTo("testName");
	}

	/**
	 * place 엔티티 생성 메서드.
	 */
	private Place createPlace(String kakaoId, String name, String address, String categoryCode,
		double x, double y, String imageLink, String telephone) {
		Place place = Place.builder()
			.kakaoId(kakaoId)
			.name(name)
			.telephone(telephone)
			.build();

		place.injectImageLink(imageLink);
		place.setPlaceCategory(categoryCode);
		place.setAddress(address);
		place.setLocation(y, x);

		return place;
	}
}