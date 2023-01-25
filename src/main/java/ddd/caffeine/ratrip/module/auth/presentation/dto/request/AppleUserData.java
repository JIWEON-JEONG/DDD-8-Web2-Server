package ddd.caffeine.ratrip.module.auth.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AppleUserData {
	private String email;
	private AppleUserName name;

}
