package ddd.caffeine.ratrip.module.notification.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.module.notification.application.dto.CreateNotificationDto;
import ddd.caffeine.ratrip.module.notification.domain.Notification;
import ddd.caffeine.ratrip.module.notification.domain.respository.NotificationRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {
	private final NotificationRepository notificationRepository;

	public Long createNotification(CreateNotificationDto request) {
		Notification notification = Notification.of(request.getTitle(), request.getContent());
		return notificationRepository.save(notification).getId();
	}
}
