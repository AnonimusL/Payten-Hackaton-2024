package com.payten.hacka.notification_service.notifiers.clients.email;

import com.payten.hacka.notification_service.domain.Message;
import org.json.JSONObject;

public class EmailMessageParser {
    public static Message parse(String jsonMessage) {
        JSONObject messageJson = new JSONObject(jsonMessage);

        String toAddress = messageJson.getString("toEmail");
        String subject = messageJson.getString("messageSubject");
        String content = messageJson.getString("messageContent");
        String base64QR = messageJson.getString("qrCode");

        return new Message(toAddress, subject, content, base64QR);
    }
}
