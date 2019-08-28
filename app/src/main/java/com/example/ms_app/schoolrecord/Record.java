package com.example.ms_app.schoolrecord;

import java.io.Serializable;

public class Record implements Serializable {
    private Integer date;
    private String arrivals;
    private String arr_time;
    private String leave;
    private String lea_time;


    private Record(){

    }

    public Record(Integer date, String arrivals, String arr_time, String leave, String lea_time) {
        this.date = date;
        this.arrivals = arrivals;
        this.arr_time = arr_time;
        this.leave = leave;
        this.lea_time = lea_time;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getArrivals() {
        return arrivals;
    }

    public void setArrivals(String arrivals) {
        this.arrivals = arrivals;
    }

    public String getArr_time() {
        return arr_time;
    }

    public void setArr_time(String arr_time) {
        this.arr_time = arr_time;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    public String getLea_time() {
        return lea_time;
    }

    public void setLea_time(String lea_time) {
        this.lea_time = lea_time;
    }
}
