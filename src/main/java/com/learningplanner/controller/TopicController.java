package com.learningplanner.controller;

import com.learningplanner.dto.SubjectRequest;
import com.learningplanner.dto.TopicRequest;
import com.learningplanner.dto.TopicUpdateRequest;
import com.learningplanner.entity.Topic;
import com.learningplanner.repository.TopicRepository;
import com.learningplanner.service.TopicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicRepository topicRepository;
    
    private TopicRequest topicRequest;

    @GetMapping("/bySubject/{subjectId}")
    public ResponseEntity<?> getBySubject(@PathVariable Long subjectId) {
        var topics = topicRepository.findAll()
                .stream()
                .filter(t -> t.getSubject().getId().equals(subjectId))
                .toList();

        return ResponseEntity.ok(topics);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTopic(
            @PathVariable Long id,
            @RequestBody TopicUpdateRequest req){
        Topic updated = topicService.updateTopic(id, req);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<?> completeTopic(@PathVariable Long id) {
        Topic completed = topicService.markComplete(id);
        return ResponseEntity.ok(completed);
    }
    
    @PostMapping("/{subjectId}/addTopic")
    public ResponseEntity<?> addTopic(@PathVariable Long subjectId,@RequestBody TopicRequest req){
        return ResponseEntity.ok(topicService.addTopic(subjectId,req));
    }
}
