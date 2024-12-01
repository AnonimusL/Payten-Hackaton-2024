package com.payten.hacka.notification_service.notifiers.clients.email;

import com.payten.hacka.notification_service.domain.Message;
import com.payten.hacka.notification_service.domain.Notification;
import com.payten.hacka.notification_service.repository.NotificationRepository;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
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
            MimeMessage mailMessage = createHTMLMime(message);

            if (mailMessage == null)
                return;

            this.javaMailSender.send(mailMessage);
            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    private MimeMessage createHTMLMime(Message message) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom("vnikolic7821rn@raf.rs");
            helper.setTo(message.getToEmail());
            helper.setSubject(message.getMessageSubject());

            byte[] qrCodeBytes = Base64.getDecoder().decode(message.getQrCode());

            ByteArrayDataSource dataSource = new ByteArrayDataSource(qrCodeBytes, "image/png");
            helper.addAttachment("qr.png", dataSource); // Content ID: qrCodeImage

            String htmlContent = "<html><body>" +
                    "<p>" + message.getMessageContent() + "</p>" +
                    "</body></html>";

            helper.setText(htmlContent, true);  // 'true' indicates the content is HTML

            javaMailSender.send(mimeMessage);
            return mimeMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
