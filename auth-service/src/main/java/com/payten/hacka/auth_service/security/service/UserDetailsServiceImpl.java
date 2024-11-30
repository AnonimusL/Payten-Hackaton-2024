package com.payten.hacka.auth_service.security.service;

import com.payten.hacka.auth_service.domain.User;
import com.payten.hacka.auth_service.mapper.UserMapper;
import com.payten.hacka.auth_service.repository.UserRepository;
import com.payten.hacka.auth_service.security.dto.UserAuthDetails;
import com.payten.hacka.auth_service.security.model.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        UserAuthDetails details = userMapper.mapToUserAuthDetails(user);

        return new SecurityUser(details);
    }

}
