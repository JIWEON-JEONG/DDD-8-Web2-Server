package ddd.caffeine.ratrip.module.notification.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UpdateUserNameDto {
	private final String newName;

	public static UpdateUserNameDto of(String newName) {
		return new UpdateUserNameDto(newName);
	}
}
