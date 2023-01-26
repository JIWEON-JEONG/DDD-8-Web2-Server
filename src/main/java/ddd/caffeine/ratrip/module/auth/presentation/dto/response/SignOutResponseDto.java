package ddd.caffeine.ratrip.module.auth.presentation.dto.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignOutResponseDto {
	private final UUID id;
}
