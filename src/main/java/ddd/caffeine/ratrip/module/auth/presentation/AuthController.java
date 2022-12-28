package ddd.caffeine.ratrip.module.auth.presentation;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.module.auth.application.AppleAuthService;
import ddd.caffeine.ratrip.module.auth.application.KakaoAuthService;
import ddd.caffeine.ratrip.module.auth.application.TokenService;
import ddd.caffeine.ratrip.module.auth.presentation.dto.request.SignInWithAppleRequestDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.request.SignInWithKakaoRequestDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.request.SignOutRequestDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.request.SignUpWithAppleRequestDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.request.SignUpWithKakaoRequestDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.request.TokenReissueRequestDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.SignInResponseDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.TokenResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AuthController {
	private final KakaoAuthService kakaoAuthService;
	private final AppleAuthService appleAuthService;
	private final TokenService tokenService;

	@PostMapping("/auth/signup/kakao")
	public ResponseEntity<SignInResponseDto> signUpWithKakao(@Valid @RequestBody SignUpWithKakaoRequestDto request) {
		return ResponseEntity.ok(kakaoAuthService.signUpWithKakao(request.toServiceDto()));
	}

	@PostMapping("/auth/signup/apple")
	public ResponseEntity<SignInResponseDto> signUpWithApple(@Valid @RequestBody SignUpWithAppleRequestDto request) {
		return ResponseEntity.ok(appleAuthService.signUpWithApple(request.toServiceDto()));
	}

	@PostMapping("/auth/signin/kakao")
	public ResponseEntity<SignInResponseDto> signInWithKakao(@Valid @RequestBody SignInWithKakaoRequestDto request) {
		return ResponseEntity.ok(kakaoAuthService.signInWithKakao(request.toServiceDto()));
	}

	@PostMapping("/auth/signin/apple")
	public ResponseEntity<SignInResponseDto> signInWithApple(@Valid @RequestBody SignInWithAppleRequestDto request) {
		return ResponseEntity.ok(appleAuthService.signInWithApple(request.toServiceDto()));
	}

	@PostMapping("/auth/reissue")
	public ResponseEntity<TokenResponseDto> reissueToken(@Valid @RequestBody TokenReissueRequestDto request) {
		return ResponseEntity.ok(tokenService.reissueToken(request.toServiceDto()));
	}

	@PostMapping("/auth/signout")
	public ResponseEntity<UUID> signOut(@Valid @RequestBody SignOutRequestDto request) {
		return ResponseEntity.ok(tokenService.deleteToken(request.toServiceDto()));
	}
}
