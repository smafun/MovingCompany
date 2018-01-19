package com.example.tuananhle.movingcompany;

/**
 * Created by tuananhle on 17.01.2018.
 */

public class ServiceTypeClass {
 /*   private String ServiceTypeId;
    private String ServiceTypeName;

    public String getServiceTypeId() {
        return this.ServiceTypeId;
    }

    public String getServiceTypeName() {
        return this.ServiceTypeName;
    } */

    private int id;
    private String content;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public ServiceTypeClass(int id, String content){
        this.id = id;
        this.content = content;
    }

}
