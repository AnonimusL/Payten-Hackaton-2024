package com.payten.hacka.notification_service;

import com.payten.hacka.notification_service.notifiers.EmailNotifier;
import com.payten.hacka.notification_service.notifiers.INotifier;
import com.payten.hacka.notification_service.notifiers.clients.email.EmailClient;
import com.payten.hacka.notification_service.notifiers.clients.email.EmailConfiguration;
import com.payten.hacka.notification_service.rmq.RMQConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);

		EmailClient emailClient = new EmailClient(new EmailConfiguration(
				"smtp.gmail.com",
				"587",
				"email",
				"pass"));

		INotifier emailNotifier = new EmailNotifier(emailClient);
		RMQConsumer rmqConsumer = new RMQConsumer("emailQueue", emailNotifier);

		rmqConsumer.start();
	}

}
