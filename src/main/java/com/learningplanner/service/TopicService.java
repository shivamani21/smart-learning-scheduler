package com.learningplanner.service;

import com.learningplanner.dto.TopicRequest;
import com.learningplanner.dto.TopicUpdateRequest;
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

    public Topic updateTopic(Long id, TopicUpdateRequest req) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        if (req.getTopicName() != null) {
            topic.setTopicName(req.getTopicName());
        }

        if (req.getScheduledDate() != null) {
            topic.setScheduledDate(LocalDate.parse(req.getScheduledDate()));
        }

        if (req.getScheduledTime() != null) {
            topic.setScheduledTime(LocalTime.parse(req.getScheduledTime()));
        }

        if (req.getSms1hrSent() != null) {
            topic.setSms1hrSent(req.getSms1hrSent());
        }

        if (req.getSms10minSent() != null) {
            topic.setSms10minSent(req.getSms10minSent());
        }

        if (req.getCompletionSmsSent() != null) {
            topic.setCompletionSmsSent(req.getCompletionSmsSent());
        }

        if (req.getStatus() != null) {
            topic.setStatus(Topic.Status.valueOf(req.getStatus().toUpperCase()));
        }

        return topicRepository.save(topic);
    }

    
    public List<Topic> addTopic(Long subjectId, TopicRequest req) {

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        // 1️⃣ Get last topic for this subject (most recent scheduledDate)
        Topic lastTopic = topicRepository
                .findTopBySubjectIdOrderByScheduledDateDesc(subjectId);

        LocalDate nextDate;

        if (lastTopic != null && lastTopic.getScheduledDate() != null) {
            nextDate = lastTopic.getScheduledDate().plusDays(1);
        } else {
            // If no topics exist yet, start from subject.startDate
            nextDate = subject.getStartDate();
        }

        LocalTime time = subject.getStartTime();

        String[] topicsArr = req.getTopics().split(",");
        List<Topic> savedTopics = new ArrayList<>();

        for (String name : topicsArr) {

            Topic topic = new Topic();
            topic.setSubject(subject);
            topic.setTopicName(name.trim());

            // 2️⃣ Assign the computed schedule date & time
            topic.setScheduledDate(nextDate);
            topic.setScheduledTime(time);

            savedTopics.add(topicRepository.save(topic));

            // 3️⃣ Move nextDate forward by 1 for next topic
            nextDate = nextDate.plusDays(1);
        }

        return savedTopics;
    }



    public Topic markComplete(Long id) {
        Topic topic = topicRepository.findById(id).orElseThrow();
        topic.setStatus(Topic.Status.COMPLETED);
        topic.setSms1hrSent(true);
        topic.setSms10minSent(true);

        return topicRepository.save(topic);
    }
}
