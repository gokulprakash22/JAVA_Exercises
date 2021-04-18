package com.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;



public class TotalDetails implements Serializable{
	private CustomerMaster customerMaster;
	private HashMap<ItemMaster,Integer> itemList;
	private int total;
	private int invid;
	private LocalDate invoiceDate;
	
	public TotalDetails() {
		// TODO Auto-generated constructor stub
	}

	public TotalDetails(CustomerMaster customerMaster, HashMap<ItemMaster, Integer> itemList, int total, int invid,
			LocalDate invoiceDate) {
		super();
		this.customerMaster = customerMaster;
		this.itemList = itemList;
		this.total = total;
		this.invid = invid;
		this.invoiceDate = invoiceDate;
	}

	public final CustomerMaster getCustomerMaster() {
		return customerMaster;
	}

	public final void setCustomerMaster(CustomerMaster customerMaster) {
		this.customerMaster = customerMaster;
	}

	public final HashMap<ItemMaster, Integer> getItemList() {
		return itemList;
	}

	public final void setItemList(HashMap<ItemMaster, Integer> itemList) {
		this.itemList = itemList;
	}

	public final int getTotal() {
		return total;
	}

	public final void setTotal(int total) {
		this.total = total;
	}

	public final int getInvid() {
		return invid;
	}

	public final void setInvid(int invid) {
		this.invid = invid;
	}

	public final LocalDate getInvoiceDate() {
		return invoiceDate;
	}

	public final void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	@Override
	public String toString() {
		return "TotalDetails [customerMaster=" + customerMaster + ", itemList=" + itemList + ", total=" + total
				+ ", invid=" + invid + ", invoiceDate=" + invoiceDate + "]";
	}
	
	
}
