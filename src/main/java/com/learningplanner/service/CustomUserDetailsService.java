package com.learningplanner.service;

import com.learningplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {

        // Use fully qualified name for your entity class
        com.learningplanner.entity.User u = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Use fully qualified name for Spring Security's User builder
        return org.springframework.security.core.userdetails.User
                .withUsername(u.getPhoneNumber())
                .password(u.getPassword())
                .authorities("USER")
                .build();
    }
}
