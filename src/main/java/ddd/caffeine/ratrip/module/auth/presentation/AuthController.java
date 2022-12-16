package ddd.caffeine.ratrip.module.auth.presentation;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.module.auth.application.AuthService;
import ddd.caffeine.ratrip.module.auth.application.AuthServiceProvider;
import ddd.caffeine.ratrip.module.auth.application.TokenService;
import ddd.caffeine.ratrip.module.auth.presentation.dto.request.SignInRequestDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.request.SignOutRequestDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.request.SignUpRequestDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.request.TokenReissueRequestDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.SignInResponseDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.TokenResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AuthController {
	private final AuthServiceProvider authServiceProvider;
	private final TokenService tokenService;

	@PostMapping("/auth/signup")
	public ResponseEntity<SignInResponseDto> signUp(@Valid @RequestBody SignUpRequestDto request) {
		AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
		SignInResponseDto responseDto = authService.signUp(request.toServiceDto());

		return ResponseEntity.ok(responseDto);
	}

	@PostMapping("/auth/signin")
	public ResponseEntity<SignInResponseDto> signIn(@Valid @RequestBody SignInRequestDto request) {
		AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
		SignInResponseDto responseDto = authService.signIn(request.toServiceDto());

		return ResponseEntity.ok(responseDto);
	}

	@PostMapping("auth/reissue")
	public ResponseEntity<TokenResponseDto> reissueToken(@Valid @RequestBody TokenReissueRequestDto request) {
		return ResponseEntity.ok(tokenService.reissueToken(request.toServiceDto()));
	}

	@PostMapping("/auth/signout")
	public ResponseEntity<UUID> signOut(@Valid @RequestBody SignOutRequestDto request) {
		UUID userId = tokenService.deleteToken(request.toServiceDto());
		return ResponseEntity.ok(userId);
	}
}
