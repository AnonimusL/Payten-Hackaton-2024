package com.payten.hacka.notification_service.service;

import com.payten.hacka.notification_service.domain.NotificationType;
import com.payten.hacka.notification_service.dto.NotificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService {
    Page<NotificationDto> getAllNotifications(Pageable pageable);
    List<NotificationDto> getNotificationsPerUser(String email);
    List<NotificationDto> getNotificationsByType(NotificationType notificationType);
}
