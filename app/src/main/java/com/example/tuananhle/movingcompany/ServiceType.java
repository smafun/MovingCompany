package com.example.tuananhle.movingcompany;

/**
 * Created by tuananhle on 17.01.2018.
 */

public class ServiceType {
    private int id;
    private String name;

    public ServiceType(int id, String name){
        this.id = id;
        this.name = name;
    }


/*    public void setServiceTypeId(String serviceTypeId) {
        this.ServiceTypeId = serviceTypeId;
    }
    public String getServiceTypeId() {
        return this.ServiceTypeId;
    }*/

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public String toString(){
        return this.name;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }
}
