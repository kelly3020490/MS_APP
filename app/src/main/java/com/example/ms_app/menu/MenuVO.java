package com.example.ms_app.menu;

import java.io.Serializable;

public class MenuVO implements Serializable {
    private String breakfast;
    private String lunch;
    private String snack;

    private MenuVO(){

    }

    public MenuVO(String breakfast, String lunch, String snack) {
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.snack = snack;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getSnack() {
        return snack;
    }

    public void setSnack(String snack) {
        this.snack = snack;
    }
}
