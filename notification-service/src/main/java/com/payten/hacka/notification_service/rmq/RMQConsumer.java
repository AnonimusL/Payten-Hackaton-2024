package com.payten.hacka.notification_service.rmq;

import com.payten.hacka.notification_service.notifiers.INotifier;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RMQConsumer {

    private final String queueName;
    private final INotifier notifier;

    public RMQConsumer(String queueName, INotifier notifier) {
        this.queueName = queueName;
        this.notifier = notifier;
    }

    public void start() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(queueName, false, false, false, null);

            System.out.println("Waiting for the carrot...");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String messageBody = new String(delivery.getBody());
                notifier.sendNotification(messageBody);
            };

            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException("Failed to start RabbitMQReceiver", e);
        }
    }
}
