package ddd.caffeine.ratrip.module.place.domain.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import ddd.caffeine.ratrip.TestConfig;
import ddd.caffeine.ratrip.common.model.Region;
import ddd.caffeine.ratrip.module.place.domain.Place;

import ddd.caffeine.ratrip.module.place.domain.bookmark.repository.BookmarkRepository;
import ddd.caffeine.ratrip.module.place.domain.repository.dao.PlaceBookmarkDao;
import ddd.caffeine.ratrip.module.user.domain.User;
import ddd.caffeine.ratrip.module.user.domain.UserSocialType;
import ddd.caffeine.ratrip.module.user.domain.UserStatus;

@DataJpaTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PlaceRepositoryTest {

	@Autowired
	PlaceRepository placeRepository;

	@Autowired
	BookmarkRepository bookmarkRepository;

	@Test
	@DisplayName("외부 서비스 ID로 장소 찾기 정상 동작 테스트")
	void findByThirdPartyIDTest() {
		//given
		Place testPlace = createPlaceExceptNotNullField("testThirdPartyID", "name", "address", "categoryCode",
			120.365, 34.678);
		placeRepository.save(testPlace);
		//when
		PlaceBookmarkDao place = placeRepository.findByThirdPartyID("testThirdPartyID");
		//then
		assertThat(place).isEqualTo(testPlace);
	}

	@Test
	@DisplayName("여러 지역의 장소 찾기 정상 동작 테스트")
	void findPlacesInRegionsTest() {
		//given
		Place 부산_스타벅스 = createPlaceExceptNotNullField("부산ThirdPartyID", "서면 스타벅스", "부산 서면 스타벅스 까페", "CF7", 1, 1);
		Place 서울_스타벅스 = createPlaceExceptNotNullField("서울ThirdPartyID", "양재 스타벅스", "서울 양재동 스타벅스 까페", "CF7", 1, 1);
		Place 인천_스타벅스 = createPlaceExceptNotNullField("인천ThirdPartyID", "부평 스타벅스", "인천 부평 스타벅스 까페", "CF7", 1, 1);
		placeRepository.save(부산_스타벅스);
		placeRepository.save(서울_스타벅스);
		placeRepository.save(인천_스타벅스);

		Pageable pageRequest = PageRequest.of(0, 3);
		List<Region> 특정지역 = List.of(Region.서울특별시, Region.부산광역시, Region.인천광역시);
		//when
		Slice<PlaceBookmarkDao> places = placeRepository.findPlacesInRegions(특정지역, pageRequest);
		//then
		assertThat(places.getContent().size()).isEqualTo(3);
	}

	@Test
	@DisplayName("특정 지역이 빈 리스트 일때 전국 조회 정상 동작 테스트")
	void findPlacesInRegionsIfEmptyListTest() {
		//given
		Place 기타Place = createPlaceExceptNotNullField("기타ThirdPartyID", "name", "testAddress", "categoryCode",
			120.365, 34.678);
		Place 양재Place = createPlaceExceptNotNullField("양재ThirdPartyID", "name", "서울 양재동 스타벅스", "categoryCode",
			120.365, 34.678);
		placeRepository.save(기타Place);
		placeRepository.save(양재Place);

		Pageable pageRequest = PageRequest.ofSize(2);
		//when
		List<Region> regions = new ArrayList<>();
		Slice<PlaceBookmarkDao> places = placeRepository.findPlacesInRegions(regions, pageRequest);
		//then
		assertThat(places.getContent().size()).isEqualTo(2);
		assertThat(places.getContent().contains(기타Place));
		assertThat(places.getContent().contains(양재Place));
	}

	@Test
	@DisplayName("특정 지역의 인기 많은 장소 찾기 정상 동작 테스트")
	void findPlacesInRegionsSortPopularTest() {
		//given
		Place seoulFamousPlace = createPlaceExceptNotNullField("강남ThirdPartyID", "강남 스타벅스", "서울 강남 스타벅스 까페", "CF7", 1,
			1);
		Place seoulPlace = createPlaceExceptNotNullField("양재ThirdPartyID", "양재 스타벅스", "서울 양재동 스타벅스 까페", "CF7", 1, 1);
		seoulFamousPlace.travelCome();
		placeRepository.save(seoulFamousPlace);
		placeRepository.save(seoulPlace);

		List<Region> 특정지역 = List.of(Region.서울특별시);
		Pageable pageRequest = PageRequest.of(0, 1, Sort.Direction.DESC, "popular");
		//when
		Slice<PlaceBookmarkDao> places = placeRepository.findPlacesInRegions(특정지역, pageRequest);
		//then
		assertThat(places.getContent().contains(seoulFamousPlace)).isTrue();
		assertThat(places.hasNext()).isTrue();
	}

	@Test
	@DisplayName("특정 지역의 인기 많은 장소 찾기 정상 동작 테스트")
	void checkQueryNPlusOneProblem() {
		//given
		Place place = createPlaceExceptNotNullField("testId", "강남 스타벅스", "서울 강남 스타벅스 까페", "CF7", 1, 1);
		User user01 = User.of("name01", "email01", UserStatus.ACTIVE, "socialId01", UserSocialType.KAKAO);
		User user02 = User.of("name02", "email02", UserStatus.ACTIVE, "socialId02", UserSocialType.KAKAO);
		// Bookmark bookmark = Bookmark.of()

	}

	/**
	 * place 엔티티 생성 메서드 (NotNull 조건 걸린 필드만 생성)
	 */
	private Place createPlaceExceptNotNullField(String thirdPartyID, String name, String address, String categoryCode,
		double x, double y) {
		Place place = Place.builder()
			.kakaoId(thirdPartyID)
			.name(name)
			.build();

		place.setCategoryByCode(categoryCode);
		place.setAddress(address);
		place.setLocation(y, x);
		return place;
	}
}