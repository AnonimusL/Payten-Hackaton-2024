package com.payten.hacka.notification_service.rmq;

import com.payten.hacka.notification_service.notifiers.INotifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RMQConfig {
    @Bean
    public RMQConsumer createConsumer(INotifier notifier) {
        RMQConsumer rmqConsumer = new RMQConsumer("emailQueue", notifier);
        rmqConsumer.start();
        return rmqConsumer;
    }
}

// TODO: Finish integration