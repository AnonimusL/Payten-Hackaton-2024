package com.payten.hacka.notification_service.notifiers;

import com.payten.hacka.notification_service.domain.Message;
import com.payten.hacka.notification_service.notifiers.clients.email.EmailClient;
import com.payten.hacka.notification_service.notifiers.clients.email.EmailMessageParser;
import org.springframework.stereotype.Component;

@Component
public class EmailNotifier implements INotifier {

    private final EmailClient emailClient;

    public EmailNotifier(EmailClient emailClient) {
        this.emailClient = emailClient;
    }

    @Override
    public void sendNotification(String message) {
        Message convertedMessage = EmailMessageParser.parse(message);
        this.emailClient.sendEmail(convertedMessage);
    }
}
