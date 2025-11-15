
package com.learningplanner.service;
import com.learningplanner.dto.TopicUpdateRequest; 
import com.learningplanner.entity.Topic; 
import com.learningplanner.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import java.time.LocalDate; import java.time.LocalTime;
@Service public class TopicService {
    @Autowired private TopicRepository topicRepository;
    public Topic updateTopic(Long id, TopicUpdateRequest req){
        Topic t = topicRepository.findById(id).orElseThrow();
        if (req.getTopicName() != null) t.setTopicName(req.getTopicName());
        if (req.getScheduledDate() != null) t.setScheduledDate(LocalDate.parse(req.getScheduledDate()));
        if (req.getScheduledTime() != null) t.setScheduledTime(LocalTime.parse(req.getScheduledTime()));
        return topicRepository.save(t);
    }
    public Topic markComplete(Long id){
        Topic t = topicRepository.findById(id).orElseThrow();
        t.setStatus(Topic.Status.COMPLETED);
        t.setSms1hrSent(true); t.setSms10minSent(true);
        return topicRepository.save(t);
    }
}
