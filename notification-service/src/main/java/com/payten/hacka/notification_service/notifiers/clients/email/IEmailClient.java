package com.payten.hacka.notification_service.notifiers.clients.email;

import com.payten.hacka.notification_service.domain.Message;

public interface IEmailClient {
    void sendEmail(Message message);
}
