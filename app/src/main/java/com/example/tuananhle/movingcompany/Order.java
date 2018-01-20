package com.example.tuananhle.movingcompany;

/**
 * Created by tuananhle on 19.01.2018.
 */

public class Order {
    private int CustomerId;
    private int ServiceId;
    private int OrderId;
    private String AddressFrom;
    private String AddressTo;
    private String ServiceTypes;
    private String Date;
    private String FreeText;


    public int getCustomerId() {
        return CustomerId;
    }
    public void setCustomerId(int customerId) {
        this.CustomerId = customerId;
    }

    public int getOrderId() {
        return OrderId;
    }
    public void setOrderId(int orderId) {
        this.OrderId = orderId;
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


    public Order(int customerid, int orderid, String addrfrom, String addrto, String email, String date, String freetxt){
        this.CustomerId = customerid;
        this.OrderId = orderid;
        this.AddressFrom = addrfrom;
        this.AddressTo = addrto;
        this.ServiceTypes = email;
        this.Date = date;
        this.FreeText = freetxt;
    }
}
