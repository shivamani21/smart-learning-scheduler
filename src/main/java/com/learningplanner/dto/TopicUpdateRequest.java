package com.learningplanner.dto;

public class TopicUpdateRequest {

    private String topicName;
    private String scheduledDate;   // yyyy-MM-dd
    private String scheduledTime;   // HH:mm:ss

    private Boolean sms1hrSent;
    private Boolean sms10minSent;
    private Boolean completionSmsSent;

    private String status; // "PENDING" or "COMPLETED"

    // Getters & Setters
    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
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

    public Boolean getSms1hrSent() {
        return sms1hrSent;
    }

    public void setSms1hrSent(Boolean sms1hrSent) {
        this.sms1hrSent = sms1hrSent;
    }

    public Boolean getSms10minSent() {
        return sms10minSent;
    }

    public void setSms10minSent(Boolean sms10minSent) {
        this.sms10minSent = sms10minSent;
    }

    public Boolean getCompletionSmsSent() {
        return completionSmsSent;
    }

    public void setCompletionSmsSent(Boolean completionSmsSent) {
        this.completionSmsSent = completionSmsSent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
