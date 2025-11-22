package com.learningplanner.service;

import com.learningplanner.dto.SubjectRequest;
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

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    public Subject addSubject(String phoneNumber, SubjectRequest req) {

        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow();

        // Create subject
        Subject subject = new Subject();
        subject.setUser(user);
        subject.setSubjectName(req.getSubjectName());
        subject.setStartDate(LocalDate.parse(req.getStartDate()));
        subject.setStartTime(LocalTime.parse(req.getStartTime()));

        Subject savedSubject = subjectRepository.save(subject);

        // Parse topics CSV
        String[] topicsArr = req.getTopics().split(",");

        LocalDate date = savedSubject.getStartDate();
        LocalTime time = savedSubject.getStartTime();

        // Create each topic for each day
        for (int i = 0; i < topicsArr.length; i++) {
            String topicName = topicsArr[i].trim();

            Topic topic = new Topic();
            topic.setSubject(savedSubject);
            topic.setTopicName(topicName);
            topic.setScheduledDate(date.plusDays(i));
            topic.setScheduledTime(time);

            topicRepository.save(topic);
        }

        return savedSubject;
    }
    public Subject updateSubject(Long subjectId, SubjectRequest req) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        if (req.getSubjectName() != null) {
            subject.setSubjectName(req.getSubjectName());
        }

        if (req.getStartDate() != null) {
            subject.setStartDate(LocalDate.parse(req.getStartDate()));
        }

        if (req.getStartTime() != null) {
            subject.setStartTime(LocalTime.parse(req.getStartTime()));
        }

        return subjectRepository.save(subject);
    }
}
