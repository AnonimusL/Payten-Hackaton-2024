package com.payten.hacka.notification_service.dto;

import com.payten.hacka.notification_service.domain.NotificationType;
import lombok.*;

@AllArgsConstructor
@Getter
public class NotificationDto {
    private String email;
    private NotificationType notificationType;
    private Long sentTimestamp;
}
