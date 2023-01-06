package ddd.caffeine.ratrip.module.auth.presentation;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	private final TokenService tokenService;

	@PostMapping("/auth/signin/kakao")
	public ResponseEntity<SignInResponseDto> signInWithKakao() {
		return ResponseEntity.ok();
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
