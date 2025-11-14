
package com.learningplanner.service;
import com.learningplanner.dto.SubjectRequest; import com.learningplanner.entity.Subject; import com.learningplanner.entity.Topic; import com.learningplanner.entity.User;
import com.learningplanner.repository.SubjectRepository; import com.learningplanner.repository.TopicRepository; import com.learningplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired; import org.springframework.stereotype.Service;
import java.time.LocalDate; import java.time.LocalTime;
@Service public class SubjectService {
    @Autowired private SubjectRepository subjectRepository; @Autowired private UserRepository userRepository; @Autowired private TopicRepository topicRepository;
    public Subject addSubject(String phoneNumber, SubjectRequest req){
        User user = userRepository.findByPhoneNumber(phoneNumber).orElseThrow();
        Subject s = new Subject(); s.setUser(user); s.setSubjectName(req.getSubjectName()); s.setStartDate(LocalDate.parse(req.getStartDate())); s.setStartTime(LocalTime.parse(req.getStartTime()));
        Subject saved = subjectRepository.save(s);
        String[] topics = req.getTopics().split(",");
        LocalDate date = saved.getStartDate(); LocalTime time = saved.getStartTime();
        for(int i=0;i<topics.length;i++){ String tname = topics[i].trim(); Topic t = new Topic(); t.setSubject(saved); t.setTopicName(tname); t.setScheduledDate(date.plusDays(i)); t.setScheduledTime(time); topicRepository.save(t); }
        return saved;
    }
}
