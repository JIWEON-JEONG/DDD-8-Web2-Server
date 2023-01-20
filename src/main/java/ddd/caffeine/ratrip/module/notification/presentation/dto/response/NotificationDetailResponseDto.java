package ddd.caffeine.ratrip.module.notification.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NotificationDetailResponseDto {
	private final Long id;
	private final String title;
	private final String contents;
}
