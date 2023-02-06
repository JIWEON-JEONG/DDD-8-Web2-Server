package ddd.caffeine.ratrip.module.place.domain.bookmark;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ddd.caffeine.ratrip.module.place.domain.Place;
import ddd.caffeine.ratrip.module.user.domain.User;
import ddd.caffeine.ratrip.module.user.domain.UserSocialType;
import ddd.caffeine.ratrip.module.user.domain.UserStatus;

class BookmarkTest {

	@Test
	@DisplayName("연관관계 편의 메서드 정상 동작 테스트")
	void createBookmarkTest() {
		//given
		Place place = createPlaceExceptNotNullField();
		User user = createActiveUser();

		//when
		Bookmark bookmark = Bookmark.of(user, place);

		//then
		assertThat(place.getBookmarks()).contains(bookmark);
	}

	/**
	 * place 엔티티 생성 메서드.
	 */
	private Place createPlaceExceptNotNullField() {
		Place place = Place.builder()
			.kakaoId("kakaoId")
			.name("name")
			.build();

		place.setCategoryByCode("CF7");
		place.setAddress("서울 강남구 역삼동 825");
		place.setLocation(37.4976744709989, 127.028443419181);
		return place;
	}

	/**
	 * User 엔티티 생성 메서드.
	 */
	private User createActiveUser() {
		return User.of("name", "email", UserStatus.ACTIVE, "socialId", UserSocialType.KAKAO);
	}

}