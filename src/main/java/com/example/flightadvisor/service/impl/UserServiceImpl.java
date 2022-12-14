package com.example.flightadvisor.service.impl;

import com.example.flightadvisor.exceptions.InvalidArgumentsException;
import com.example.flightadvisor.exceptions.UsernameAlreadyExistsException;
import com.example.flightadvisor.model.User;
import com.example.flightadvisor.repository.UserRepository;
import com.example.flightadvisor.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> register(String username, String password, String firstName, String lastName) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentsException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);

        User user = new User(username, passwordEncoder.encode(password), firstName, lastName);
        return Optional.of(userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
