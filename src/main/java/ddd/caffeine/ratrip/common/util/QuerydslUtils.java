package ddd.caffeine.ratrip.common.util;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;

public class QuerydslUtils {
	public static OrderSpecifier<?> getSortedColumn(Order order, Path<?> parent, String fieldName) {
		Path<Object> fieldPath = Expressions.path(Object.class, parent, fieldName);

		return new OrderSpecifier(order, fieldPath);
	}

	public static <T> Slice<T> toSlice(List<T> contents, Pageable pageable) {
		boolean hasNext = isContentSizeGreaterThanPageSize(contents, pageable);
		return new SliceImpl<>(hasNext ? subListLastContent(contents, pageable) : contents, pageable, hasNext);
	}

	// 다음 페이지 있는지 확인
	private static <T> boolean isContentSizeGreaterThanPageSize(List<T> content, Pageable pageable) {
		return pageable.isPaged() && content.size() > pageable.getPageSize();
	}

	// 데이터 1개 빼고 반환
	private static <T> List<T> subListLastContent(List<T> content, Pageable pageable) {
		return content.subList(0, pageable.getPageSize());
	}
}
