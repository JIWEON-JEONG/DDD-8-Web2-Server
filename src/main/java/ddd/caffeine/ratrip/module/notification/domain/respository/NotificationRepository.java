package ddd.caffeine.ratrip.module.notification.domain.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import ddd.caffeine.ratrip.module.notification.domain.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>, NotificationQueryRepository {
}
