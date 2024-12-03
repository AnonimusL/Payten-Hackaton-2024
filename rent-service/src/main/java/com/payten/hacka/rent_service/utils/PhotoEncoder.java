package com.payten.hacka.rent_service.utils;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class PhotoEncoder {
    public String getImageAsBase64(byte[] imageData) {
        return Base64.getEncoder().encodeToString(imageData);
    }
}
