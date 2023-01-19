package ddd.caffeine.ratrip.module.user.presentation.dto.request;

import javax.validation.constraints.NotBlank;

import ddd.caffeine.ratrip.module.notification.application.dto.UpdateUserNameDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UpdateUserNameRequestDto {
	@NotBlank(message = "New name must not be blank")
	private String newName;

	public UpdateUserNameDto toServiceDto() {
		return UpdateUserNameDto.of(newName);
	}
}
