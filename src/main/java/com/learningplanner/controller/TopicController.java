package com.learningplanner.controller;
import com.learningplanner.dto.TopicUpdateRequest;
import com.learningplanner.entity.Topic; 
import com.learningplanner.repository.TopicRepository; 
import com.learningplanner.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/topics")
public class TopicController {
    @Autowired private TopicService topicService;
    @Autowired private TopicRepository topicRepository;
    @GetMapping("/bySubject/{subjectId}") public ResponseEntity<?> getBySubject(@PathVariable Long subjectId){ var subj = topicRepository.findAll().stream().filter(t->t.getSubject().getId().equals(subjectId)).toList(); return ResponseEntity.ok(subj); }
    @PutMapping("/{id}") public ResponseEntity<?> updateTopic(@PathVariable Long id, @RequestBody TopicUpdateRequest req){ Topic t = topicService.updateTopic(id, req); return ResponseEntity.ok(t); }
    @PutMapping("/{id}/complete") public ResponseEntity<?> completeTopic(@PathVariable Long id){ Topic t = topicService.markComplete(id); return ResponseEntity.ok(t); }
}