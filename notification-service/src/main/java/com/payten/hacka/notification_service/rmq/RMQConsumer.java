package com.payten.hacka.notification_service.rmq;

import com.payten.hacka.notification_service.domain.Message;
import com.payten.hacka.notification_service.domain.Notification;
import com.payten.hacka.notification_service.domain.NotificationType;
import com.payten.hacka.notification_service.helpers.MessageParser;
import com.payten.hacka.notification_service.notifiers.INotifier;
import com.payten.hacka.notification_service.repository.NotificationRepository;
import com.rabbitmq.client.*;
import org.aspectj.weaver.ast.Not;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

@Component
public class RMQConsumer {

    private final INotifier notifier;
    private final NotificationRepository notificationRepository;

    public RMQConsumer (INotifier notifier, NotificationRepository notificationRepository) {
        this.notifier = notifier;
        this.notificationRepository = notificationRepository;
    }

    @RabbitListener(queues = "application_topic_queue")
    public void handleMessage(String message, @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey) {
        switch (routingKey) {
            case "account.reset_password":
                handleResetPassword(message);
                saveNotification(message, NotificationType.RESET_PASSWORD);
                break;
            case "account.validate_email":
                System.out.println("AHAHAAAA");
                handleValidateEmail(message);
                saveNotification(message, NotificationType.CONFIRM_ACCOUNT);
                break;
            case "rental.rent":
                System.out.println("TU SAM");
                handleRent(message);
                saveNotification(message, NotificationType.RENTAL_ACTION);
                break;
            default:
                System.out.println("Routing key invalid " + routingKey);
        }
    }

    private boolean saveNotification(String message, NotificationType notificationType) {
        try {
            Message message1 = MessageParser.parse(message);
            this.notificationRepository.save(new Notification(message1.getToEmail(), notificationType, new Date().getTime()));
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private void handleResetPassword(String message) {
        return;
    }

    private void handleValidateEmail(String message) {
        return;
    }

    private void handleRent(String message) {
        this.notifier.sendNotification(message);
    }
}
