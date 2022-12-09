package ddd.caffeine.ratrip.module.auth.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.module.auth.application.AuthService;
import ddd.caffeine.ratrip.module.auth.application.AuthServiceProvider;
import ddd.caffeine.ratrip.module.auth.presentation.dto.request.SignUpRequestDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.SignInResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	private final AuthServiceProvider authServiceProvider;

	@PostMapping("/auth/signup")
	public ResponseEntity<SignInResponseDto> signUp(@Valid @RequestBody SignUpRequestDto request) {
		AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
		SignInResponseDto responseDto = authService.signUp(request.toServiceDto());

		return ResponseEntity.ok(responseDto);
	}
}
