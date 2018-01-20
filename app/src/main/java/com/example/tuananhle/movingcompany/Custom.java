package com.example.tuananhle.movingcompany;

/**
 * Created by tuananhle on 19.01.2018.
 */

public class Custom {
    private int Id;
    private String CustomName;
    private String CustomPhone;
    private String CustomEmail;

    public int getId() {
        return Id;
    }
    public void setId(int customId) {
        this.Id = customId;
    }

    public String getCustomName() {
        return CustomName;
    }

    public void setCustomName(String customName) {
        this.CustomName = customName;
    }

    public String getCustomPhone() {
        return CustomPhone;
    }

    public void setCustomPhone(String customPhone) {
        this.CustomPhone = customPhone;
    }

    public String getCustomEmail() {
        return CustomEmail;
    }

    public void setCustomEmail(String customEmail) {
        this.CustomEmail = customEmail;
    }

    public Custom(int id, String customname, String customphone, String customemail){
        this.Id = id;
        this.CustomName = customname;
        this.CustomPhone = customphone;
        this.CustomEmail = customemail;
    }
}
