package com.inventoryserver.core;

public class Return {

	private int returnId;
	private String productId;
	private int qtyReturned;
	private String returnReason;
	private int orderId;
	private String returnDate;
	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	Return(){}
	
	
	public int getReturnId() {
		return returnId;
	}
	public void setReturnId(int returnId) {
		this.returnId = returnId;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public int getQtyReturned() {
		return qtyReturned;
	}
	public void setQtyReturned(int qtyReturned) {
		this.qtyReturned = qtyReturned;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	
	
}
