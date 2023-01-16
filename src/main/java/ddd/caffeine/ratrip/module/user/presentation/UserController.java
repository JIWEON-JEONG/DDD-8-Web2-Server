package ddd.caffeine.ratrip.module.user.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	@GetMapping("/health-check")
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("health check success");
	}

	@GetMapping("/test")
	public ResponseEntity<String> test2(@AuthenticationPrincipal User user) {
		return ResponseEntity.ok("유저 이름 = " + user.getName());
	}
}
