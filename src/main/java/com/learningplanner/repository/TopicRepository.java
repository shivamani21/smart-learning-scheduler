package com.learningplanner.repository;

import com.learningplanner.entity.Topic;
//import com.learningplanner.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    // ✅ OLD (kept if you need Subject object)
//    List<Topic> findBySubjectOrderByScheduledDateAsc(Subject subject);

    // ✅ NEW (BEST for controller usage)
    List<Topic> findBySubjectIdOrderByScheduledDateAscScheduledTimeAsc(Long subjectId);

    List<Topic> findByStatusAndScheduledDate(Topic.Status status, LocalDate date);

    Topic findTopBySubjectIdOrderByScheduledDateDesc(Long subjectId);
}
