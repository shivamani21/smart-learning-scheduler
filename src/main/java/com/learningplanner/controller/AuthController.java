
package com.learningplanner.controller;
import com.learningplanner.dto.*; import com.learningplanner.entity.User; import com.learningplanner.repository.UserRepository;
import com.learningplanner.service.AuthService; import com.learningplanner.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired; import org.springframework.http.ResponseEntity; import org.springframework.security.authentication.*; import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth")
public class AuthController {
    @Autowired private AuthService authService; @Autowired private AuthenticationManager authManager; @Autowired private JwtUtil jwtUtil; @Autowired private UserRepository userRepository;
    @PostMapping("/register") public ResponseEntity<?> register(@RequestBody RegisterRequest req){ User u=authService.register(req); return ResponseEntity.ok(u); }
    @PostMapping("/login") public ResponseEntity<?> login(@RequestBody LoginRequest req){ Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getPhoneNumber(), req.getPassword())); String token = jwtUtil.generateToken(req.getPhoneNumber()); return ResponseEntity.ok(java.util.Map.of("token", token)); }
}
