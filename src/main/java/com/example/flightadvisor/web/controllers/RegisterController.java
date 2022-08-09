package com.example.flightadvisor.web.controllers;

import com.example.flightadvisor.model.User;
import com.example.flightadvisor.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> register(@RequestBody User user){
        return this.userService.register(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName())
                .map(user1 -> ResponseEntity.ok().body(user1))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
