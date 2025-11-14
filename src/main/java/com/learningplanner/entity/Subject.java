
package com.learningplanner.entity;
import jakarta.persistence.*;
import java.time.LocalDate; import java.time.LocalTime;
@Entity @Table(name="subjects")
public class Subject {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne private User user;
    @Column(name="subject_name") private String subjectName;
    private LocalDate startDate; private LocalTime startTime;
    // getters/setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public User getUser(){return user;} public void setUser(User u){this.user=u;}
    public String getSubjectName(){return subjectName;} public void setSubjectName(String s){this.subjectName=s;}
    public LocalDate getStartDate(){return startDate;} public void setStartDate(LocalDate d){this.startDate=d;}
    public LocalTime getStartTime(){return startTime;} public void setStartTime(LocalTime t){this.startTime=t;}
}
