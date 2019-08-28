package com.example.ms_app.main;

import java.io.Serializable;

public class Member implements Serializable {
    private String st_num;
    private String st_password;

    public Member() {
        super();
    }

    public Member(String st_num,String st_password) {
        super();
        this.st_num = st_num;
        this.st_password = st_password;
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



}

