
package com.learningplanner.scheduler;
import com.learningplanner.service.ReminderService; import org.springframework.scheduling.annotation.Scheduled; import org.springframework.stereotype.Component;
@Component public class ReminderScheduler {
    private final ReminderService reminderService;
    public ReminderScheduler(ReminderService reminderService){ this.reminderService = reminderService; }
    @Scheduled(fixedRate = 60000) public void run(){ try{ reminderService.processReminders(); }catch(Exception e){ e.printStackTrace(); } }
}
