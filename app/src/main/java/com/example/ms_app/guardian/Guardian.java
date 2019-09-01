package com.example.ms_app.guardian;

import java.io.Serializable;

public class Guardian implements Serializable {

    private String gd_id;
    private String gd_name;
    private String gd_gender;
    private String gd_email;
    private String gd_address;
    private String gd_rel;
    private String gd_phone;
    private java.sql.Date gd_birthday;


    public Guardian() {
        super();
    }


    public String getGd_id() {
        return gd_id;
    }


    public void setGd_id(String gd_id) {
        this.gd_id = gd_id;
    }


    public String getGd_name() {
        return gd_name;
    }


    public void setGd_name(String gd_name) {
        this.gd_name = gd_name;
    }


    public String getGd_gender() {
        return gd_gender;
    }


    public void setGd_gender(String gd_gender) {
        this.gd_gender = gd_gender;
    }


    public String getGd_email() {
        return gd_email;
    }


    public void setGd_email(String gd_email) {
        this.gd_email = gd_email;
    }


    public String getGd_address() {
        return gd_address;
    }


    public void setGd_address(String gd_address) {
        this.gd_address = gd_address;
    }


    public String getGd_rel() {
        return gd_rel;
    }


    public void setGd_rel(String gd_rel) {
        this.gd_rel = gd_rel;
    }


    public String getGd_phone() {
        return gd_phone;
    }


    public void setGd_phone(String gd_phone) {
        this.gd_phone = gd_phone;
    }


    public java.sql.Date getGd_birthday() {
        return gd_birthday;
    }


    public void setGd_birthday(java.sql.Date gd_birthday) {
        this.gd_birthday = gd_birthday;
    }



}
