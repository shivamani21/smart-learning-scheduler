
package com.learningplanner.entity;
import jakarta.persistence.*;
import java.time.LocalDate; import java.time.LocalTime;
@Entity @Table(name="topics")
public class Topic {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne private Subject subject;
    @Column(name="topic_name") private String topicName;
    private LocalDate scheduledDate; private LocalTime scheduledTime;
    @Enumerated(EnumType.STRING) private Status status = Status.PENDING;
    private boolean sms1hrSent=false; private boolean sms10minSent=false; private boolean completionSmsSent=false;
    public enum Status{PENDING,COMPLETED}
    // getters/setters (omitted for brevity in file to keep length small)
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Subject getSubject(){return subject;} public void setSubject(Subject s){this.subject=s;}
    public String getTopicName(){return topicName;} public void setTopicName(String t){this.topicName=t;}
    public java.time.LocalDate getScheduledDate(){return scheduledDate;} public void setScheduledDate(java.time.LocalDate d){this.scheduledDate=d;}
    public java.time.LocalTime getScheduledTime(){return scheduledTime;} public void setScheduledTime(java.time.LocalTime t){this.scheduledTime=t;}
    public Status getStatus(){return status;} public void setStatus(Status s){this.status=s;}
    public boolean isSms1hrSent(){return sms1hrSent;} public void setSms1hrSent(boolean v){this.sms1hrSent=v;}
    public boolean isSms10minSent(){return sms10minSent;} public void setSms10minSent(boolean v){this.sms10minSent=v;}
    public boolean isCompletionSmsSent(){return completionSmsSent;} public void setCompletionSmsSent(boolean v){this.completionSmsSent=v;}
}
