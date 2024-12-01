package com.payten.hacka.notification_service.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "notifications", indexes = {@Index(columnList = "email")})
@NoArgsConstructor
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Email
    private String email;

    @Enumerated
    private NotificationType notificationType;
    private Long sentDatetime;

    public Notification(String email, NotificationType notificationType, Long sentDatetime) {
        this.email = email;
        this.notificationType = notificationType;
        this.sentDatetime = sentDatetime;
    }
}
