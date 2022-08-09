package com.example.flightadvisor.service;

import com.example.flightadvisor.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
//    User login(String username, String password);
//
    Optional<User> register(String username, String password, String firstName, String lastName);
}
