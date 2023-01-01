package ddd.caffeine.ratrip.module.place.repository;

import static ddd.caffeine.ratrip.module.place.model.QPlace.*;

import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import ddd.caffeine.ratrip.module.place.model.Place;
import ddd.caffeine.ratrip.module.place.model.Region;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlaceQueryRepositoryImpl implements PlaceQueryRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public List<Place> findPopularPlace(Region region, Integer limit) {
		return jpaQueryFactory
			.selectFrom(place)
			.where(regionEq(region))
			.orderBy(place.numberOfTrips.desc())
			.limit(limit)
			.fetch();
	}

	private BooleanExpression regionEq(Region region) {
		return region != null ? place.address.region.eq(region) : null;
	}
}
