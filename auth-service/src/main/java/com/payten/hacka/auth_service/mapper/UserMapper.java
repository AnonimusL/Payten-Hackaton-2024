package com.payten.hacka.auth_service.mapper;

import com.payten.hacka.auth_service.domain.Company;
import com.payten.hacka.auth_service.domain.User;
import com.payten.hacka.auth_service.repository.CompanyRepository;
import com.payten.hacka.auth_service.repository.UserRepository;
import com.payten.hacka.auth_service.security.dto.UserAuthDetails;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserAuthDetails mapToUserAuthDetails(User user) {
        String companyName = null;
        if(user.getCompany() != null) companyName = user.getCompany().getName();
        return new UserAuthDetails(user.getEmail(), user.getPassword(), user.getRole().name(), companyName);
    }
}
