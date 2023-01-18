package ddd.caffeine.ratrip.module.notification.domain.respository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import ddd.caffeine.ratrip.module.notification.presentation.dto.response.NotificationDto;

public interface NotificationQueryRepository {
	Slice<NotificationDto> findNotificationsUsingSlice(Pageable pageable);
}
