package com.payten.hacka.auth_service.security.dto;

import jakarta.annotation.Nullable;
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
    private final String role;
    @Nullable
    private final String company;
}