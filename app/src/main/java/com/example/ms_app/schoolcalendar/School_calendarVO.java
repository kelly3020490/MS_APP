package com.example.ms_app.schoolcalendar;

import java.io.Serializable;

public class School_calendarVO implements Serializable {
    private Integer date;
    private String activity;
    private String startTime;
    private String endTime;

    private School_calendarVO(){

    }

    public School_calendarVO(Integer date, String activity, String startTime, String endTime) {
        this.date = date;
        this.activity = activity;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
