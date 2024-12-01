package com.payten.hacka.auth_service.dto;

import com.payten.hacka.auth_service.domain.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDetailDto {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private AddressDto address;
}
