package com.learningplanner.service;

import com.learningplanner.dto.TopicRequest;
import com.learningplanner.entity.Subject;
import com.learningplanner.entity.Topic;
import com.learningplanner.entity.User;
import com.learningplanner.repository.SubjectRepository;
import com.learningplanner.repository.TopicRepository;
import com.learningplanner.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;
    
    @Autowired
    private ReminderService reminderService;
    

    public Topic updateTopic(Long id, TopicRequest req) {

        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        Topic.Status oldStatus = topic.getStatus();

        if (req.getTopicName() != null) {
            topic.setTopicName(req.getTopicName());
        }

        if (req.getScheduledDate() != null) {
            topic.setScheduledDate(LocalDate.parse(req.getScheduledDate()));
        }

        if (req.getScheduledTime() != null) {
            topic.setScheduledTime(LocalTime.parse(req.getScheduledTime()));
        }

        if (req.getStatus() != null) {
            topic.setStatus(Topic.Status.valueOf(req.getStatus().toUpperCase()));
        }

        Topic savedTopic = topicRepository.save(topic);

        // ✅ SEND COMPLETION MESSAGE ONLY ONCE
        if (oldStatus == Topic.Status.PENDING &&
            savedTopic.getStatus() == Topic.Status.COMPLETED &&
            !savedTopic.isCompletionSmsSent()) {

            reminderService.sendCompletionMessage(savedTopic);
        }

        return savedTopic;
    }

    public List<Topic> addTopic(Long subjectId, TopicRequest req) {

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Topic lastTopic = topicRepository
                .findTopBySubjectIdOrderByScheduledDateDesc(subjectId);

        LocalDate nextDate = (lastTopic != null && lastTopic.getScheduledDate() != null)
                ? lastTopic.getScheduledDate().plusDays(1)
                : subject.getStartDate();

        LocalTime time = subject.getStartTime();

        // read topics from the updated DTO
        String[] topicsArr = req.getTopicName().split(",");

        List<Topic> savedTopics = new ArrayList<>();

        for (String name : topicsArr) {
            Topic topic = new Topic();
            topic.setSubject(subject);
            topic.setTopicName(name.trim());
            topic.setScheduledDate(nextDate);
            topic.setScheduledTime(time);

            savedTopics.add(topicRepository.save(topic));

            nextDate = nextDate.plusDays(1);
        }

        return savedTopics;
    }

    public Topic markComplete(Long id) {

        Topic topic = topicRepository.findById(id).orElseThrow();

        topic.setStatus(Topic.Status.COMPLETED);

        topicRepository.save(topic);

        reminderService.sendCompletionMessage(topic);

        return topic;
    }
}
