
package com.learningplanner.service;
import com.learningplanner.entity.User; import com.learningplanner.repository.UserRepository; import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*; import org.springframework.stereotype.Service;
@Service public class CustomUserDetailsService implements UserDetailsService {
    @Autowired private UserRepository userRepository;
    @Override public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User u = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return User.withUsername(u.getPhoneNumber()).password(u.getPassword()).authorities("USER").build();
    }
}
