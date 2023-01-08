package ddd.caffeine.ratrip.module.auth.presentation;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.module.auth.application.KakaoAuthService;
import ddd.caffeine.ratrip.module.auth.application.TokenService;
import ddd.caffeine.ratrip.module.auth.presentation.dto.request.SignOutRequestDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.request.TokenReissueRequestDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.SignInResponseDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.TokenResponseDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AuthController {
	private final KakaoAuthService kakaoAuthService;
	private final TokenService tokenService;

	@GetMapping("/auth/signin/kakao")
	public ResponseEntity<SignInResponseDto> signInWithKakao(@RequestHeader("code") String code) {
		return ResponseEntity.ok(kakaoAuthService.signIn(code));
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
