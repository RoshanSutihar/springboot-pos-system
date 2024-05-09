package com.inventoryserver.core;

public class Supplier {
	private int supplierId;
	private String supplierName;
	private String supplierContact;
	private int supplierAddress;
	private String supplierEmail;
	
	Supplier(){}
	
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierContact() {
		return supplierContact;
	}
	public void setSupplierContact(String supplierContact) {
		this.supplierContact = supplierContact;
	}
	public int getSupplierAddress() {
		return supplierAddress;
	}
	public void setSupplierAddress(int supplierAddress) {
		this.supplierAddress = supplierAddress;
	}
	public String getSupplierEmail() {
		return supplierEmail;
	}
	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}
	
	

}
