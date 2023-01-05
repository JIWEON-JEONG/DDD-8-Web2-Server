package ddd.caffeine.ratrip.module.user.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	@GetMapping("/")
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("health check success");
	}
}
