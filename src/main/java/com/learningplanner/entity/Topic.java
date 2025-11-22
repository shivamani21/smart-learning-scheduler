package com.learningplanner.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Subject subject;

    @Column(name = "topic_name")
    private String topicName;

    private LocalDate scheduledDate;
    private LocalTime scheduledTime;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private boolean sms1hrSent = false;
    private boolean sms10minSent = false;
    private boolean completionSmsSent = false;

    public enum Status {
        PENDING,
        COMPLETED
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public LocalTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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
