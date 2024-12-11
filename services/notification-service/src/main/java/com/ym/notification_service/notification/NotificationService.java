package com.ym.notification_service.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void addNotification(NotificationType notificationType){
        Notification notification = Notification.builder()
                .type(notificationType).build();
        notificationRepository.save(notification);
    }
}
