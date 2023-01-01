package ddd.caffeine.ratrip.module.place.model;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegionTest {

	@Test
	@DisplayName("NullPointException 발생하지 않고 정상적으로 리턴 되는지 확인 테스트")
	void createRegionsTest() {
		//given
		String keyword = "EMPTY 반환합니다.";
		//when
		List<Region> regions = Region.createRegions(List.of(keyword));
		//then
		assertThat(regions).isEmpty();
	}
}