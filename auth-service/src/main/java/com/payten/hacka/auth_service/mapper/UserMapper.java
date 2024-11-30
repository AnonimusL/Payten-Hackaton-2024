package com.payten.hacka.auth_service.mapper;

import com.payten.hacka.auth_service.domain.User;
import com.payten.hacka.auth_service.security.dto.UserAuthDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserAuthDetails mapToUserAuthDetails(User user) {
        return new UserAuthDetails();
        //return new UserAuthDetails(user.getEmail(), user.getPassword(), new ArrayList<>(), user.getRole().getName().name(), user.getOrganizations().stream()
         //       .map(Organization::getId)
           //     .collect(Collectors.toList()));
    }
}
