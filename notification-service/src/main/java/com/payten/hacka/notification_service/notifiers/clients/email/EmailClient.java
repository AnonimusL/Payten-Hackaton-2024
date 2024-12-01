package com.payten.hacka.notification_service.notifiers.clients.email;

import com.payten.hacka.notification_service.domain.Message;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailClient implements IEmailClient {

    private final EmailConfiguration emailConfiguration;
    private final Session clientSession;

    public EmailClient (EmailConfiguration emailConfiguration) {
        this.emailConfiguration = emailConfiguration;
        this.clientSession = authenticate();
    }

    private Session authenticate() {
        return Session.getInstance(this.emailConfiguration.getEmailProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailConfiguration.getUsername(), emailConfiguration.getPassword());
            }
        });
    }

    @Override
    public void sendEmail(Message message) {
        try {

            jakarta.mail.Message mimeMessage = new MimeMessage(this.clientSession);
            mimeMessage.setFrom(new InternetAddress(this.emailConfiguration.getUsername()));
            mimeMessage.setRecipients(jakarta.mail.Message.RecipientType.TO, InternetAddress.parse(message.getToEmail()));
            mimeMessage.setSubject(message.getMessageSubject());
            mimeMessage.setText(message.getMessageContent());

            Transport.send(mimeMessage);

            System.out.println("Email sent!");
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
