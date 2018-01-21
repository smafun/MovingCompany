package com.example.tuananhle.movingcompany;

/**
 * Created by tuananhle on 19.01.2018.
 */

public class Order {
    private String CustomName;
    private int ServiceId;
    private int Id;
    private String AddressFrom;
    private String AddressTo;
    private String ServiceTypes;
    private String Date;
    private String FreeText;


    public String getCustomName() {
        return CustomName;
    }
    public void setCustomName(String customname) {
        this.CustomName = customname;
    }

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        this.Id = id;
    }

    public String getAddressFrom() {
        return AddressFrom;
    }
    public void setAddressFrom(String addresseFrom) {
        this.AddressFrom = addresseFrom;
    }

    public String getAddresseTo() {
        return AddressTo;
    }
    public void setAddresseTo(String addresseTo) {
        this.AddressTo = addresseTo;
    }

    public String getServiceTypes() {
        return ServiceTypes;
    }
    public void setServiceTypes(String serviceTypes) {
        this.ServiceTypes = serviceTypes;
    }

    public String getDate() {
        return Date;
    }
    public void setDate(String date) {
        this.Date = date;
    }

    public String getFreeText() {
        return FreeText;
    }
    public void setFreeText(String freeText) {
        this.FreeText = freeText;
    }

    public Order(int id, String customName, String addrfrom, String addrto, String email, String date, String freetxt){
    this.Id = id;
    this.CustomName = customName;
    this.AddressFrom = addrfrom;
    this.AddressTo = addrto;
    this.ServiceTypes = email;
    this.Date = date;
    this.FreeText = freetxt;
    }
}
