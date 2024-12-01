package com.payten.hacka.notification_service.notifiers.clients.email;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Properties;

@RequiredArgsConstructor
@Getter
public class EmailConfiguration {
    private final String host;
    private final String port;
    private final String username;
    private final String password;

    public Properties getEmailProperties() {
        Properties properties = new Properties();

        properties.put("mail.smtp.host", this.host);
        properties.put("mail.smtp.port", this.port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        return properties;
    }
}
