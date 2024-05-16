package com.inventoryserver.core;

import java.util.Date;
import java.util.List;

public class Order {
    private int orderID;
    private String orderDate;
    private String customerID;
    private String shipDate;
    private double totalAmount;
    private String orderStatus;
    private String paymentType;
    public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	private List<OrderDetail> orderDetails;

 
    public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Order(int orderID, String orderDate, String customerID, String shipDate, double totalAmount, List<OrderDetail> orderDetails) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.shipDate = shipDate;
        this.totalAmount = totalAmount;
        this.orderDetails = orderDetails;
    }

    Order(){}
 
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

 
  

   

    public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}


	public String getShipDate() {
		return shipDate;
	}


	public void setShipDate(String shipDate) {
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
