package ddd.caffeine.ratrip.module.place.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlaceQueryRepositoryImpl implements PlaceQueryRepository {

	private final JPAQueryFactory jpaQueryFactory;
}
