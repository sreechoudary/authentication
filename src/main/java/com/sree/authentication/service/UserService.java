package com.sree.authentication.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sree.authentication.model.User;
import com.sree.authentication.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
