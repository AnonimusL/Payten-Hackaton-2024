package com.payten.hacka.notification_service.rmq;

import com.payten.hacka.notification_service.notifiers.INotifier;
import com.rabbitmq.client.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class RMQConsumer {

    private final INotifier notifier;

    public RMQConsumer (INotifier notifier) {
        this.notifier = notifier;
    }

    @RabbitListener(queues = "application_topic_queue")
    public void handleMessage(String message, @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey) {
        switch (routingKey) {
            case "account.reset_password":
                handleResetPassword(message);
                break;
            case "account.validate_email":
                System.out.println("AHAHAAAA");
                handleValidateEmail(message);
                break;
            case "rental.rent":
                System.out.println("TU SAM");
                handleRent(message);
                break;
            default:
                System.out.println("Routing key invalid " + routingKey);
        }
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
