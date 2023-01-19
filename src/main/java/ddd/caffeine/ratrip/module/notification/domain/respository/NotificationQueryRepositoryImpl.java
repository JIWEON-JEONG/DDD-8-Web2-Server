package ddd.caffeine.ratrip.module.notification.domain.respository;

import static ddd.caffeine.ratrip.module.notification.domain.QNotification.*;
import static ddd.caffeine.ratrip.module.place.model.QPlace.*;
import static org.springframework.util.ObjectUtils.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;

import ddd.caffeine.ratrip.common.util.QuerydslUtils;
import ddd.caffeine.ratrip.module.notification.presentation.dto.response.NotificationDto;
import ddd.caffeine.ratrip.module.notification.presentation.dto.response.QNotificationDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotificationQueryRepositoryImpl implements NotificationQueryRepository {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Slice<NotificationDto> findNotificationsUsingSlice(Pageable pageable) {
		List<NotificationDto> contents = jpaQueryFactory.
			select(new QNotificationDto(notification))
			.from(notification)
			.orderBy(readOrderSpecifiers(pageable).toArray(OrderSpecifier[]::new))
			.offset(pageable.getOffset())
			.limit(pageable.getOffset() + 1)
			.fetch();

		return QuerydslUtils.toSlice(contents, pageable);
	}

	private List<OrderSpecifier> readOrderSpecifiers(Pageable pageable) {
		List<OrderSpecifier> orders = new ArrayList<>();

		if (!isEmpty(pageable.getSort())) {
			for (Sort.Order order : pageable.getSort()) {
				Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

				switch (order.getProperty()) {
					case "createdAt":
						OrderSpecifier<?> createdAt = QuerydslUtils
							.getSortedColumn(direction, place, "createdAt");
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
