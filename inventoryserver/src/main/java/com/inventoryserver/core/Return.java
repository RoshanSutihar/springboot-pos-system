package com.inventoryserver.core;

public class Return {

	private int returnId;
	private int productId;
	private int qtyReturned;
	private String returnReason;
	
	Return(){}
	
	
	public int getReturnId() {
		return returnId;
	}
	public void setReturnId(int returnId) {
		this.returnId = returnId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
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
	private String returnDate;
	
}
