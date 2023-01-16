package ddd.caffeine.ratrip.module.bookmark.domain.repository;

import static ddd.caffeine.ratrip.module.bookmark.domain.QBookmark.*;

import com.querydsl.jpa.impl.JPAQueryFactory;

import ddd.caffeine.ratrip.module.bookmark.domain.Bookmark;
import ddd.caffeine.ratrip.module.place.model.Place;
import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookmarkQueryRepositoryImpl implements BookmarkQueryRepository {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public long deleteByUserAndPlace(User user, Place place) {
		return jpaQueryFactory.delete(bookmark)
			.where(bookmark.user.eq(user), bookmark.place.eq(place))
			.execute();
	}

	@Override
	public Bookmark findByPlaceAndUser(User user, Place place) {
		return jpaQueryFactory.selectFrom(bookmark)
			.where(bookmark.user.eq(user), bookmark.place.eq(place))
			.fetchOne();
	}
}
