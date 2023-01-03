package ddd.caffeine.ratrip.module.user.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	@GetMapping("/test")
	public String test() {
		return "health check";
	}
}
