package com.payten.hacka.notification_service.notifiers.clients.email;

import com.payten.hacka.notification_service.domain.Message;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class EmailClient implements IEmailClient {

    private final JavaMailSender javaMailSender;

    public EmailClient (JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(Message message) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(message.getToEmail());
            helper.setSubject(message.getMessageSubject());
            helper.setText(message.getMessageContent());
            helper.setFrom("opremise123@gmail.com");

            javaMailSender.send(mimeMessage);
            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
