package com.payten.hacka.notification_service.mapper;

import com.payten.hacka.notification_service.domain.Notification;
import com.payten.hacka.notification_service.dto.NotificationDto;

public class NotificationMapper implements IMapper<Notification, NotificationDto> {

    public NotificationMapper() {
    }

    @Override
    public NotificationDto toDto(Notification notification) {
        return new NotificationDto(notification.getEmail(),
                notification.getNotificationType(),
                notification.getSentDatetime());
    }

    @Override
    public Notification fromDto(NotificationDto notificationDto) {
        return new Notification(notificationDto.getEmail(),
                notificationDto.getNotificationType(),
                notificationDto.getSentTimestamp());
    }
}
