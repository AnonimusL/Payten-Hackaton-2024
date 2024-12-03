package com.payten.hacka.notification_service.rmq;

import com.payten.hacka.notification_service.notifiers.INotifier;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;

@Configuration
public class RMQConfig {

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("application_exchange_new");
    }

    @Bean
    public Queue topicQueue() {
        return new Queue("application_topic_queue_new", true, false, true);
    }

    @Bean
    public Binding bindingResetPassword(Queue topicQueue, TopicExchange exchange) {
        return BindingBuilder.bind(topicQueue).to(exchange).with("account.reset_password");
    }

    @Bean
    public Binding bindingValidateEmail(Queue topicQueue, TopicExchange exchange) {
        return BindingBuilder.bind(topicQueue).to(exchange).with("account.validate_email");
    }

    @Bean
    public Binding bindingRent(Queue topicQueue, TopicExchange exchange) {
        return BindingBuilder.bind(topicQueue).to(exchange).with("rental.rent");
    }
}

// TODO: Finish integration