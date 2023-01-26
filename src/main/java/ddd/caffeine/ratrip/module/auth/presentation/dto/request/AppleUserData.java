package ddd.caffeine.ratrip.module.auth.presentation.dto.request;

import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AppleUserData {
	@Email
	private String email;
	private AppleUserName name;

}
