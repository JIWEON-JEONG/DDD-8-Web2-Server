package ddd.caffeine.ratrip.module.notification.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

@Getter
public class NotificationDto {
	private final Long id;
	private final String title;

	@QueryProjection
	public NotificationDto(Long id, String title) {
		this.id = id;
		this.title = title;
	}
}
