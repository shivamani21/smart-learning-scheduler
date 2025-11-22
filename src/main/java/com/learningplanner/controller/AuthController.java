package com.learningplanner.controller;

import com.learningplanner.dto.*;
import com.learningplanner.entity.User;
import com.learningplanner.repository.UserRepository;
import com.learningplanner.service.AuthService;
import com.learningplanner.config.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    // Helper function to normalize phone numbers
    private String normalizePhone(String raw) {
        if (raw == null) return null;

        raw = raw.trim();

        // If user enters 10 digits like: 9398106351
        if (raw.length() == 10 && raw.matches("\\d+")) {
            raw = "+91" + raw;
        }

        return raw;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {

        // Normalize phone number before saving
        String formatted = normalizePhone(req.getPhoneNumber());
        req.setPhoneNumber(formatted);

        User user = authService.register(req);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {

        // Normalize before authentication
        String formatted = normalizePhone(req.getPhoneNumber());
        req.setPhoneNumber(formatted);

        // Authenticate
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getPhoneNumber(),
                        req.getPassword()
                )
        );

        // Generate token
        String token = jwtUtil.generateToken(req.getPhoneNumber());

        return ResponseEntity.ok(
                java.util.Map.of("token", token)
        );
    }
}
