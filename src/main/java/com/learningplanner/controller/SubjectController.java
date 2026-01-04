package com.learningplanner.controller;

import com.learningplanner.dto.SubjectRequest;
import com.learningplanner.entity.Subject;
import com.learningplanner.entity.User;
import com.learningplanner.repository.UserRepository;
import com.learningplanner.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subjects")

public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> addSubject(@RequestBody SubjectRequest req,
                                        Authentication authentication) {
        String phone = authentication.getName();
        Subject s = subjectService.addSubject(phone, req);
        return ResponseEntity.ok(s);
    }

    @Autowired
    private com.learningplanner.repository.SubjectRepository subjectRepository;
    @GetMapping
    public ResponseEntity<?> getMine(Authentication authentication) {
        String phone = authentication.getName();
        User user = userRepository.findByPhoneNumber(phone)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));
        return ResponseEntity.ok(subjectRepository.findByUser(user));
    }

    
    @PutMapping("/{subjectId}")
    public ResponseEntity<?> updateSubject(
            @PathVariable Long subjectId,
            @RequestBody SubjectRequest req) {

        return ResponseEntity.ok(subjectService.updateSubject(subjectId, req));
    }

}
