
package com.learningplanner.service;
import com.learningplanner.dto.RegisterRequest; import com.learningplanner.entity.User; import com.learningplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.stereotype.Service;
@Service
public class AuthService {
    @Autowired private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public User register(RegisterRequest req){
        if(userRepository.existsByPhoneNumber(req.getPhoneNumber())) throw new RuntimeException("Phone already registered");
        User u=new User(); u.setName(req.getName()); u.setPhoneNumber(req.getPhoneNumber()); u.setPassword(passwordEncoder.encode(req.getPassword()));
        return userRepository.save(u);
    }
}
