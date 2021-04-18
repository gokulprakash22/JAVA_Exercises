package com.model;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InvoiceMaster implements Cloneable, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int invid;
	
	private LocalDate invoiceDate;
	private int customerId;
	private Blob totalDetails;
	
	public InvoiceMaster() {
		// TODO Auto-generated constructor stub
	}
	
	public InvoiceMaster(int invid, LocalDate invoiceDate, int customerId) {
		super();
		this.invid = invid;
		this.invoiceDate = invoiceDate;
		this.customerId = customerId;
	}

	public InvoiceMaster getClone() {
		try {
			return (InvoiceMaster) super.clone();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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

	public final int getCustomerId() {
		return customerId;
	}

	public final void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public final Blob getTotalDetails() {
		return totalDetails;
	}

	public final void setTotalDetails(Blob totalDetails) {
		this.totalDetails = totalDetails;
	}
	
	
	@Override
	public String toString() {
		return "InvoiceMaster [invid=" + invid + ", invoiceDate=" + invoiceDate + ", customerId=" + customerId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + customerId;
		result = prime * result + invid;
		result = prime * result + ((invoiceDate == null) ? 0 : invoiceDate.hashCode());
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
		InvoiceMaster other = (InvoiceMaster) obj;
		if (customerId != other.customerId)
			return false;
		if (invid != other.invid)
			return false;
		if (invoiceDate == null) {
			if (other.invoiceDate != null)
				return false;
		} else if (!invoiceDate.equals(other.invoiceDate))
			return false;
		return true;
	}

	

	

}
