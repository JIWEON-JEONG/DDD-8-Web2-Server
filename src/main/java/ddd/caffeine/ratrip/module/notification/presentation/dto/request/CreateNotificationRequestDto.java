package ddd.caffeine.ratrip.module.notification.presentation.dto.request;

import javax.validation.constraints.NotBlank;

import ddd.caffeine.ratrip.module.notification.application.dto.CreateNotificationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateNotificationRequestDto {
	@NotBlank(message = "Title must not be blank")
	private String title;

	@NotBlank(message = "Content must not be blank")
	private String content;

	public CreateNotificationDto toServiceDto() {
		return CreateNotificationDto.of(title, content);
	}
}
