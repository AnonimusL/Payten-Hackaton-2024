package com.payten.hacka.notification_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Message {
    private String toEmail;
    private String messageSubject;
    private String messageContent;
    private String qrCode;
}
