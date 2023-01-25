package ddd.caffeine.ratrip.module.auth.presentation;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.module.auth.application.AuthService;
import ddd.caffeine.ratrip.module.auth.presentation.dto.request.SignInWithAppleRequestDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.request.SignOutRequestDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.request.TokenReissueRequestDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.SignInResponseDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.SignOutResponseDto;
import ddd.caffeine.ratrip.module.auth.presentation.dto.response.TokenResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	/**
	 * 아래 값들은 테스트를 위한 값으로 테스트 이후 삭제할 예정
	 */
	@Value("${APPLE_CLIENT_ID}")
	private String appleClientId;

	@Value("${APPLE_REDIRECT_URI}")
	private String appleRedirectUri;

	private final AuthService authService;

	@Operation(summary = "카카오 로그인")
	@GetMapping("/signin/kakao")
	public ResponseEntity<SignInResponseDto> signInWithKakao(@RequestParam("code") String code) {
		return ResponseEntity.ok(authService.signInWithKakao(code));
	}

	@Operation(summary = "[테스트용] 애플 로그인 API 호출 (테스트 이후 삭제 예정)")
	@GetMapping("/call")
	public String call() {
		return "https://appleid.apple.com/auth/authorize?" + "client_id=" + appleClientId + "&redirect_uri="
			+ appleRedirectUri + "&response_type=code%20id_token&scope=name%20email&response_mode=form_post&nonce="
			+ UUID.randomUUID();
	}

	@Operation(summary = "애플 로그인")
	@PostMapping("/signin/apple")
	public ResponseEntity<SignInResponseDto> signInWithApple(@Valid @RequestBody SignInWithAppleRequestDto request) {
		return ResponseEntity.ok(authService.signInWithApple(request.toServiceDto()));
	}

	@Operation(summary = "엑세스 토큰 재발급")
	@PostMapping("/reissue")
	public ResponseEntity<TokenResponseDto> reissueToken(@Valid @RequestBody TokenReissueRequestDto request) {
		return ResponseEntity.ok(authService.reissueToken(request.toServiceDto()));
	}

	@Operation(summary = "로그아웃")
	@PostMapping("/auth/signout")
	public ResponseEntity<SignOutResponseDto> signOut(@Valid @RequestBody SignOutRequestDto request) {
		return ResponseEntity.ok(authService.signOut(request.toServiceDto()));
	}
}
