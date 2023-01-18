package ddd.caffeine.ratrip.module.notification.domain.respository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ddd.caffeine.ratrip.module.notification.domain.Notification;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
}
