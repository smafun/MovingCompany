package com.example.tuananhle.movingcompany;

public class Order {
    private int customerId;
    private int id;
    private String addressFrom;
    private String addressTo;
    private String serviceTypes;
    private String date;
    private String txtField;

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomName(int customerid) {
        this.customerId = customerid;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getAddressFrom() {
        return addressFrom;
    }
    public void setAddressFrom(String addresseFrom) {
        this.addressFrom = addresseFrom;
    }

    public String getAddresseTo() {
        return addressTo;
    }
    public void setAddresseTo(String addresseTo) {
        this.addressTo = addresseTo;
    }

    public String getServiceTypes() {
        return serviceTypes;
    }
    public void setServiceTypes(String serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getFreeText() {
        return txtField;
    }
    public void setFreeText(String freeText) {
        this.txtField = freeText;
    }

    public String toString(){
        return this.customerId + " " + this.addressFrom + " " + this.addressTo + " " + this.serviceTypes + " " + this.date + " " + txtField;
    }

}
