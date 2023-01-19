package ddd.caffeine.ratrip.module.notification.presentation;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ddd.caffeine.ratrip.module.notification.application.NotificationService;
import ddd.caffeine.ratrip.module.notification.presentation.dto.request.CreateNotificationRequestDto;
import ddd.caffeine.ratrip.module.notification.presentation.dto.response.NotificationDetailResponseDto;
import ddd.caffeine.ratrip.module.notification.presentation.dto.response.NotificationsResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {
	private final NotificationService notificationService;

	@Operation(summary = "공지사항 추가")
	@PostMapping("")
	public ResponseEntity<Long> createNotification(@RequestBody CreateNotificationRequestDto request) {
		return ResponseEntity.ok(notificationService.createNotification(request.toServiceDto()));
	}

	@Operation(summary = "공지사항 전체 조회")
	@GetMapping("")
	public ResponseEntity<NotificationsResponseDto> getNotifications(
		@PageableDefault(size = 8, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
		return ResponseEntity.ok(notificationService.getNotifications(pageable));
	}

	@Operation(summary = "공지사항 세부 조회")
	@GetMapping("/{id}")
	public ResponseEntity<NotificationDetailResponseDto> getNotificationDetail(@PathVariable Long id) {
		return ResponseEntity.ok(notificationService.getNotificationDetail(id));
	}
}
