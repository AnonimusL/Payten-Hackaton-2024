package com.payten.hacka.auth_service.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
public class UserAuthDetails {
    private final String email;
    private final String password;
    private final List<String> permissions;
    private final String role;
    private final List<Long> organizations;
}