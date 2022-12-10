package ddd.caffeine.ratrip.module.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {
	@GetMapping("/test")
	public String test() {
		System.out.println("test");
		return "yes";
	}
}
