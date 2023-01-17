package ddd.caffeine.ratrip.module.place.repository;

import static ddd.caffeine.ratrip.module.place.model.QPlace.*;
import static org.springframework.util.ObjectUtils.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import ddd.caffeine.ratrip.common.util.QuerydslUtils;
import ddd.caffeine.ratrip.module.place.model.Category;
import ddd.caffeine.ratrip.module.place.model.Place;
import ddd.caffeine.ratrip.module.place.model.Region;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlaceQueryRepositoryImpl implements PlaceQueryRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Slice<Place> findPlacesInRegions(List<Region> regions, Pageable pageable) {
		List<Place> contents = jpaQueryFactory
			.selectFrom(place)
			.where(regionsIn(regions))
			.orderBy(readOrderSpecifiers(pageable).toArray(OrderSpecifier[]::new))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize() + 1)
			.fetch();

		return QuerydslUtils.toSlice(contents, pageable);
	}

	@Override
	public Slice<Place> findPlacesInCategories(List<Category> categories, Pageable pageable) {
		List<Place> contents = jpaQueryFactory
			.selectFrom(place) //TODO - Dto로 받을 수 있을까
			.where(categoriesIn(categories))
			.orderBy(readOrderSpecifiers(pageable).toArray(OrderSpecifier[]::new))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize() + 1)
			.fetch();

		return QuerydslUtils.toSlice(contents, pageable);
	}

	private BooleanExpression regionsIn(List<Region> region) {
		return region.isEmpty() ? null : place.address.region.in(region);
	}

	private BooleanExpression categoriesIn(List<Category> categories) {
		return categories.isEmpty() ? null : place.category.in(categories);
	}

	private List<OrderSpecifier> readOrderSpecifiers(Pageable pageable) {
		List<OrderSpecifier> orders = new ArrayList<>();

		if (!isEmpty(pageable.getSort())) {
			for (Sort.Order order : pageable.getSort()) {
				Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

				switch (order.getProperty()) {
					case "popular":
						OrderSpecifier<?> numberOfTrips = QuerydslUtils
							.getSortedColumn(direction, place, "numberOfTrips");
						orders.add(numberOfTrips);
						break;
					case "name":
						OrderSpecifier<?> createdAt = QuerydslUtils
							.getSortedColumn(direction, place, "name");
						orders.add(createdAt);
						break;
					default:
						break;
				}
			}
		}
		return orders;
	}
}
