package com.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ItemTransactionMaster implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemtransactionid;
	
	private int invoiceId;
	private int itemId;
	private int itemQuantity;
	
	public ItemTransactionMaster() {
		// TODO Auto-generated constructor stub
	}

	public ItemTransactionMaster(int itemtransactionid, int invoiceId, int itemId, int itemQuantity) {
		super();
		this.itemtransactionid = itemtransactionid;
		this.invoiceId = invoiceId;
		this.itemId = itemId;
		this.itemQuantity = itemQuantity;
	}

	public ItemTransactionMaster getClone() {
		try {
			return (ItemTransactionMaster) super.clone();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public final int getItemtransactionid() {
		return itemtransactionid;
	}

	public final void setItemtransactionid(int itemtransactionid) {
		this.itemtransactionid = itemtransactionid;
	}

	public final int getInvoiceId() {
		return invoiceId;
	}

	public final void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public final int getItemId() {
		return itemId;
	}

	public final void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public final int getItemQuantity() {
		return itemQuantity;
	}

	public final void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + itemtransactionid;
		result = prime * result + invoiceId;
		result = prime * result + itemId;
		result = prime * result + itemQuantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemTransactionMaster other = (ItemTransactionMaster) obj;
		if (itemtransactionid != other.itemtransactionid)
			return false;
		if (invoiceId != other.invoiceId)
			return false;
		if (itemId != other.itemId)
			return false;
		if (itemQuantity != other.itemQuantity)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ItemTransactionMaster [itemtransactionid=" + itemtransactionid + ", invoiceId=" + invoiceId + ", itemId=" + itemId
				+ ", itemQuantity=" + itemQuantity + "]";
	}
	
}
