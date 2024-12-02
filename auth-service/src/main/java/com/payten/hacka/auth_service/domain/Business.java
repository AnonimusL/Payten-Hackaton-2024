package com.payten.hacka.auth_service.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "business",
        indexes = {
                @Index(name = "idx_name", columnList = "name"),
                @Index(name = "idx_company_id", columnList = "company_id"),
                @Index(name="idx_category", columnList = "category_name")
        }
)
@Getter
@NoArgsConstructor
@Setter
public class Business extends BaseEntity {
    @ManyToOne
    private Category category;
    private String name;
    private String nameEn;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
    @ManyToMany
    @JoinTable(
            name = "business_manager",
            joinColumns = @JoinColumn(name="business_id"),
            inverseJoinColumns = @JoinColumn(name="manager_id")
    )
    private List<User> managers;

    @ManyToMany
    @JoinTable(
            name = "business_addresses",
            joinColumns = @JoinColumn(name="business_id"),
            inverseJoinColumns = @JoinColumn(name="address_id")
    )
    private List<Address> addresses;

    public Business(Category category, String name, Company company, List<User> managers) {
        this.category = category;
        this.name = name;
        this.company = company;
        this.managers = managers;
    }
}
