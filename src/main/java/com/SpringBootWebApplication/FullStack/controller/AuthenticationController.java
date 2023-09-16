package com.SpringBootWebApplication.FullStack.controller;

import com.SpringBootWebApplication.FullStack.Communication.AuthRequest;
import com.SpringBootWebApplication.FullStack.Communication.AuthResponse;
import com.SpringBootWebApplication.FullStack.entity.User;
import com.SpringBootWebApplication.FullStack.exception.BadCredentialsException;
import com.SpringBootWebApplication.FullStack.security.TokenProvider;
import com.SpringBootWebApplication.FullStack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        try {
            manager.authenticate(authenticationToken);
        } catch (BadCredentialsException exception) {
            throw new BadCredentialsException("Invalid credentials", exception);
        }
        UserDetails details = userDetailsService.loadUserByUsername(request.getUsername());
        String token = tokenProvider.generateToken(details);

        AuthResponse response = AuthResponse.builder()
                .token(token)
                .username(details.getUsername())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public User newUser(@RequestBody User user){
        return service.setUser(user);
    }
}
