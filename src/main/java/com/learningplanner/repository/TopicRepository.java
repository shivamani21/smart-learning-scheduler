
package com.learningplanner.repository;
import com.learningplanner.entity.Topic; import com.learningplanner.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate; import java.time.LocalTime; import java.util.List;
public interface TopicRepository extends JpaRepository<Topic,Long>{
    List<Topic> findBySubjectOrderByScheduledDateAsc(Subject subject);
    List<Topic> findByStatusAndScheduledDate(Topic.Status status, LocalDate date);
}
