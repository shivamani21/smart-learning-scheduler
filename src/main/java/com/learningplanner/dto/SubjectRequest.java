
package com.learningplanner.dto;
public class SubjectRequest {
    private String subjectName; private String startDate; private String startTime; private String topics;
    public String getSubjectName(){return subjectName;} public void setSubjectName(String s){this.subjectName=s;}
    public String getStartDate(){return startDate;} public void setStartDate(String d){this.startDate=d;}
    public String getStartTime(){return startTime;} public void setStartTime(String t){this.startTime=t;}
    public String getTopics(){return topics;} public void setTopics(String t){this.topics=t;}
}
