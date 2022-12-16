package ddd.caffeine.ratrip.module.user.presentation;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.module.user.domain.User;
import ddd.caffeine.ratrip.module.user.domain.repository.UserRepository;
import ddd.caffeine.ratrip.module.user.presentation.dto.request.TestRequestDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserRepository userRepository;

	@PostMapping("/test")
	public UUID test(@RequestBody TestRequestDto request) {
		User user = userRepository.findUserById(request.getUserId());
		return user.getId();
	}
}
