package ddd.caffeine.ratrip.module.notification.presentation.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NotificationsResponseDto {
	private final List<NotificationDto> notifications;
	private final boolean hasNext;
}
