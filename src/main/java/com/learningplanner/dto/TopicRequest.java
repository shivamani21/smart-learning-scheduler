package com.learningplanner.dto;

public class TopicRequest {
    private String topicName; 
    private String scheduledDate;
    private String scheduledTime;
    private boolean sms1hrSent;
    private boolean sms10minSent;
    private boolean completionSmsSent;
    private String status; // PENDING or COMPLETED

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicname) {
        this.topicName = topicname;
    }

    public String getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSms1hrSent() {
        return sms1hrSent;
    }

    public void setSms1hrSent(boolean sms1hrSent) {
        this.sms1hrSent = sms1hrSent;
    }

    public boolean isSms10minSent() {
        return sms10minSent;
    }

    public void setSms10minSent(boolean sms10minSent) {
        this.sms10minSent = sms10minSent;
    }

    public boolean isCompletionSmsSent() {
        return completionSmsSent;
    }

    public void setCompletionSmsSent(boolean completionSmsSent) {
        this.completionSmsSent = completionSmsSent;
    }
}
