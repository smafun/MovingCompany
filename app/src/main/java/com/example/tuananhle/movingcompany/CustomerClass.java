package com.example.tuananhle.movingcompany;

/**
 * Created by tuananhle on 19.01.2018.
 */

public class CustomerClass {
    private int CustomerId;
    private String CustomerName;
    private String CustomerPhone;
    private String CustomerEmail;
    private int OrderId;


    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        this.CustomerId = customerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        this.CustomerName = customerName;
    }

    public String getCustomerPhone() {
        return CustomerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.CustomerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.CustomerEmail = customerEmail;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        this.OrderId = orderId;
    }



    public CustomerClass(int customerid, String customername, String customerphone, String customeremail, int orderid){
        this.CustomerId = customerid;
        this.CustomerName = customername;
        this.CustomerPhone = customerphone;
        this.CustomerEmail = customeremail;
        this.OrderId = orderid;
    }
}
