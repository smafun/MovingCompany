package com.example.tuananhle.movingcompany;

public class Customer {
    private int id;
    private String name;
    private String phone;
    private String email;

    public int getId() {
        return id;
    }
    public void setId(int customId) {
        this.id = customId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customer(int id, String customname, String customphone, String customemail){
        this.id = id;
        this.name = customname;
        this.phone = customphone;
        this.email = customemail;
    }

    public String toString(){
        return this.name + " " + this.phone + " " + this.email;
    }
}
