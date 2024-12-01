package com.payten.hacka.notification_service.notifiers;

import com.payten.hacka.notification_service.domain.Message;

public interface INotifier {
    void sendNotification(String message);
}
