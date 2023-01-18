package ddd.caffeine.ratrip.module.notification.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.module.notification.application.NotificationService;
import ddd.caffeine.ratrip.module.notification.presentation.dto.request.CreateNotificationRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/nofiications")
@RequiredArgsConstructor
public class NotificationController {
	private final NotificationService notificationService;

	@Operation(summary = "공지사항 추가")
	@PostMapping("")
	public ResponseEntity<Long> createNotification(@RequestBody CreateNotificationRequestDto request) {
		return ResponseEntity.ok(notificationService.createNotification(request.toServiceDto()));
	}
}
