package ddd.caffeine.ratrip.module.notification.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

@Getter
public class NotificationDto {
	private final String title;

	@QueryProjection
	public NotificationDto(String title) {
		this.title = title;
	}
}
