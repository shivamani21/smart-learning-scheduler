
package com.learningplanner.service;
import com.learningplanner.entity.Topic; import com.learningplanner.repository.TopicRepository;
import com.twilio.Twilio; import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service;
import java.time.LocalDate; import java.time.LocalDateTime; import java.time.LocalTime; import java.util.List;
@Service public class ReminderService {
    private final TopicRepository topicRepository;
    @Value("${twilio.accountSid:}") private String accountSid;
    @Value("${twilio.authToken:}") private String authToken;
    @Value("${twilio.fromNumber:+1234567890}") private String fromNumber;
    public ReminderService(TopicRepository topicRepository){ this.topicRepository = topicRepository; }
    private void initTwilio(){ if (accountSid==null || accountSid.isBlank()) return; Twilio.init(accountSid, authToken); }
    public void sendSms(String to, String body){
        try { initTwilio(); if (accountSid==null || accountSid.isBlank()){ System.out.println("TWILIO not configured. SMS to:"+to+" => "+body); return; }
            Message.creator(new com.twilio.type.PhoneNumber(to), new com.twilio.type.PhoneNumber(fromNumber), body).create();
        } catch (Exception e){ e.printStackTrace(); }
    }
    public void processReminders(){
        LocalDate today = LocalDate.now();
        List<Topic> topics = topicRepository.findByStatusAndScheduledDate(Topic.Status.PENDING, today);
        LocalDateTime now = LocalDateTime.now();
        for(Topic t: topics){
            LocalDateTime dt = LocalDateTime.of(t.getScheduledDate(), t.getScheduledTime());
            if(!t.isSms1hrSent() && (now.plusHours(1).isAfter(dt) || now.plusHours(1).isEqual(dt))){
                sendSms(t.getSubject().getUser().getPhoneNumber(), "Reminder: '"+t.getTopicName()+"' starts in 1 hour at "+t.getScheduledTime());
                t.setSms1hrSent(true); topicRepository.save(t);
            }
            if(!t.isSms10minSent() && (now.plusMinutes(10).isAfter(dt) || now.plusMinutes(10).isEqual(dt))){
                sendSms(t.getSubject().getUser().getPhoneNumber(), "Reminder: '"+t.getTopicName()+"' starts in 10 minutes at "+t.getScheduledTime());
                t.setSms10minSent(true); topicRepository.save(t);
            }
            if(t.getStatus()==Topic.Status.COMPLETED && !t.isCompletionSmsSent()){
                sendSms(t.getSubject().getUser().getPhoneNumber(), "Completed: '"+t.getTopicName()+"' completed successfully.");
                t.setCompletionSmsSent(true); topicRepository.save(t);
            }
        }
    }
}
