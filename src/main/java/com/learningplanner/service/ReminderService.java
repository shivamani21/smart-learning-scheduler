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

    public void processReminders() {

        LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();

        List<Topic> topics =
                topicRepository.findByStatusAndScheduledDate(
                        Topic.Status.PENDING, today);

        for (Topic t : topics) {

            LocalDateTime taskTime =
                    LocalDateTime.of(t.getScheduledDate(), t.getScheduledTime());

            String phone = t.getSubject().getUser().getPhoneNumber();

            // ===============================
            // 1️⃣ 1-HOUR REMINDER (ONLY ONCE)
            // ===============================
            if (!t.isSms1hrSent()) {

                LocalDateTime oneHourBefore = taskTime.minusHours(1);

                if (!now.isBefore(oneHourBefore) &&
                    now.isBefore(oneHourBefore.plusMinutes(1))) {

                    whatsappService.sendWhatsAppMessage(
                            phone,
                            "⏳ Reminder: '" + t.getTopicName() +
                                    "' starts in 1 hour at " + t.getScheduledTime()
                    );

                    t.setSms1hrSent(true);
                    topicRepository.save(t);
                    continue;
                }
            }

            // ===============================
            // 2️⃣ 10-MIN REMINDER (ONLY ONCE)
            // ===============================
            if (!t.isSms10minSent()) {

                LocalDateTime tenMinBefore = taskTime.minusMinutes(10);

                if (!now.isBefore(tenMinBefore) &&
                    now.isBefore(tenMinBefore.plusMinutes(1))) {

                    whatsappService.sendWhatsAppMessage(
                            phone,
                            "⏳ Reminder: '" + t.getTopicName() +
                                    "' starts in 10 minutes at " + t.getScheduledTime()
                    );

                    t.setSms10minSent(true);
                    topicRepository.save(t);
                }
            }
        }
    }

    // ===============================
    // 3️⃣ COMPLETION MESSAGE (ONLY ONCE)
    // ===============================
    public void sendCompletionMessage(Topic t) {

        if (t.isCompletionSmsSent()) return;

        String phone = t.getSubject().getUser().getPhoneNumber();

        whatsappService.sendWhatsAppMessage(
                phone,
                "✅ Completed: '" + t.getTopicName() + "' completed successfully!"
        );

        t.setCompletionSmsSent(true);
        t.setSms1hrSent(true);
        t.setSms10minSent(true);

        topicRepository.save(t);
    }


}
