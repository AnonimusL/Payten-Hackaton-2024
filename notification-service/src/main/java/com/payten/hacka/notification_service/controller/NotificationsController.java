package com.payten.hacka.notification_service.controller;

import com.payten.hacka.notification_service.domain.NotificationType;
import com.payten.hacka.notification_service.dto.NotificationDto;
import com.payten.hacka.notification_service.service.NotificationService;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/notifications")
public class NotificationsController {

    private final NotificationService notificationService;

    public NotificationsController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/")
    public ResponseEntity<Page<NotificationDto>> getAllNotifications(Pageable pageable) {
        return new ResponseEntity<>(this.notificationService.getAllNotifications(pageable), HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<NotificationDto>> getByNotificationType(@PathVariable("type") NotificationType type) {
        return new ResponseEntity<>(this.notificationService.getNotificationsByType(type), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<NotificationDto>> getByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(this.notificationService.getNotificationsPerUser(email), HttpStatus.OK);
    }
}
