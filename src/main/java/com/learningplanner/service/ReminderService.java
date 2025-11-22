package com.learningplanner.service;

import com.learningplanner.entity.Topic;
import com.learningplanner.repository.TopicRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReminderService {

    private final TopicRepository topicRepository;
    private final WhatsAppMessageService whatsappService;

    public ReminderService(TopicRepository topicRepository,
                           WhatsAppMessageService whatsappService) {
        this.topicRepository = topicRepository;
        this.whatsappService = whatsappService;
    }

    private void sendWhatsApp(String to, String body) {
        whatsappService.sendWhatsAppMessage(to, body);
    }

    public void processReminders() {

        LocalDate today = LocalDate.now();
        List<Topic> topics = topicRepository.findByStatusAndScheduledDate(
                Topic.Status.PENDING,
                today
        );

        LocalDateTime now = LocalDateTime.now();

        for (Topic t : topics) {

            LocalDateTime dt =
                    LocalDateTime.of(t.getScheduledDate(), t.getScheduledTime());

            String phone = t.getSubject().getUser().getPhoneNumber();

            // ✔ 1 hour reminder
            if (!t.isSms1hrSent() &&
                    (now.plusHours(1).isAfter(dt) || now.plusHours(1).isEqual(dt))) {

                sendWhatsApp(
                        phone,
                        "⏳ *Reminder:* '" + t.getTopicName() +
                                "' starts in *1 hour* at " + t.getScheduledTime()
                );

                t.setSms1hrSent(true);
                topicRepository.save(t);
            }

            // ✔ 10-minute reminder
            if (!t.isSms10minSent() &&
                    (now.plusMinutes(10).isAfter(dt) || now.plusMinutes(10).isEqual(dt))) {

                sendWhatsApp(
                        phone,
                        "⏳ *Reminder:* '" + t.getTopicName() +
                                "' starts in *10 minutes* at " + t.getScheduledTime()
                );

                t.setSms10minSent(true);
                topicRepository.save(t);
            }

            // ✔ Completion reminder
            if (t.getStatus() == Topic.Status.COMPLETED &&
                    !t.isCompletionSmsSent()) {

                sendWhatsApp(
                        phone,
                        "✅ *Completed:* '" + t.getTopicName() + "' completed successfully!"
                );

                t.setCompletionSmsSent(true);
                topicRepository.save(t);
            }
        }
    }
}
