package com.payten.hacka.auth_service.security.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AuthenticationResponseDTO {
    private final String jwt;
}
