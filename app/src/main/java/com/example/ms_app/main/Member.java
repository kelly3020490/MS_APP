package com.example.ms_app.main;

import java.io.Serializable;

public class Member implements Serializable {
    private String st_num;
    private String st_password;
    private String st_name;
    private String gd_id;
    private int st_photo;

    public Member() {
        super();
    }

    public Member(String st_num,String st_password,String gd_id,int st_photo) {
        super();
        this.st_num = st_num;
        this.st_password = st_password;
        this.gd_id = gd_id;
        this.st_photo = st_photo;
    }

    public String getSt_num() {
        return st_num;
    }

    public void setSt_num(String st_num) {
        this.st_num = st_num;
    }

    public String getSt_password() {
        return st_password;
    }

    public void setSt_password(String st_password) {
        this.st_password = st_password;
    }

    public String getGd_id() {
        return gd_id;
    }

    public void setGd_id(String gd_id) {
        this.gd_id = gd_id;
    }

    public int getSt_photo() {
        return st_photo;
    }

    public void setSt_photo(int st_photo) {
        this.st_photo = st_photo;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }



}

