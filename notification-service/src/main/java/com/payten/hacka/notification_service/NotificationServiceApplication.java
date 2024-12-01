package com.payten.hacka.notification_service;

import com.payten.hacka.notification_service.notifiers.EmailNotifier;
import com.payten.hacka.notification_service.notifiers.INotifier;
import com.payten.hacka.notification_service.notifiers.clients.email.EmailClient;
import com.payten.hacka.notification_service.notifiers.clients.email.EmailConfiguration;
import com.payten.hacka.notification_service.rmq.RMQConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(EmailConfiguration.class)
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

}
