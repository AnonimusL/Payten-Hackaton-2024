package com.payten.hacka.notification_service.service;

import com.payten.hacka.notification_service.domain.Notification;
import com.payten.hacka.notification_service.domain.NotificationType;
import com.payten.hacka.notification_service.dto.NotificationDto;
import com.payten.hacka.notification_service.mapper.IMapper;
import com.payten.hacka.notification_service.repository.NotificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;
    private IMapper<Notification, NotificationDto> notificationMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   IMapper<Notification, NotificationDto> notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public Page<NotificationDto> getAllNotifications(Pageable pageable) {
        return this.notificationRepository
                .findAll(pageable)
                .map(notificationMapper::toDto);
    }

    @Override
    public List<NotificationDto> getNotificationsPerUser(String email) {
        return this.notificationRepository
                .findAll()
                .stream()
                .filter(notification -> notification.getEmail().equals(email))
                .map(this.notificationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDto> getNotificationsByType(NotificationType notificationType) {
        return this.notificationRepository
                .findAll()
                .stream()
                .filter(notification -> notification.getNotificationType().equals(notificationType))
                .map(this.notificationMapper::toDto)
                .collect(Collectors.toList());
    }
}
