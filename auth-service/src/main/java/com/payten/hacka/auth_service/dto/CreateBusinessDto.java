package com.payten.hacka.auth_service.dto;

import com.payten.hacka.auth_service.domain.Address;
import com.payten.hacka.auth_service.domain.Category;
import com.payten.hacka.auth_service.domain.Company;
import com.payten.hacka.auth_service.domain.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBusinessDto {
    @NotBlank
    private String category;
    private String categoryEn;
    @NotBlank
    private String name;
    private String nameEn;
    @NotBlank
    private UUID company;
    private List<UUID> managers;
    @NotNull
    private List<UUID> addresses;
}
