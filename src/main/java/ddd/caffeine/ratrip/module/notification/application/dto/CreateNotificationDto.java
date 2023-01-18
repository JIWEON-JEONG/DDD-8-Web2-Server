package ddd.caffeine.ratrip.module.notification.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CreateNotificationDto {
	private final String title;
	private final String content;

	public static CreateNotificationDto of(String title, String content) {
		return new CreateNotificationDto(title, content);
	}
}
