package com.inventoryserver.core;

import java.util.Date;
import java.util.List;

public class Order {
    private int orderID;
    private Date orderDate;
    private int customerID;
    private Date shipDate;
    private double totalAmount;
    private List<OrderDetail> orderDetails;

 
    public Order(int orderID, Date orderDate, int customerID, Date shipDate, double totalAmount, List<OrderDetail> orderDetails) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.shipDate = shipDate;
        this.totalAmount = totalAmount;
        this.orderDetails = orderDetails;
    }

 
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
