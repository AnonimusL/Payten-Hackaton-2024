package com.payten.hacka.rent_service.security.dto;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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