package com.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CustomerMaster implements Comparable<CustomerMaster>, Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerid;
	
	private String customerName;
	private String customerAddress;
	private String customerPhone;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerid", referencedColumnName = "uid")
    private User user;
	
	public CustomerMaster() {
		// TODO Auto-generated constructor stub
	}
	
	public CustomerMaster(int customerid, String customerName, String customerAddress,
			String customerPhone) {
		super();
		this.customerid = customerid;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerPhone = customerPhone;
	}

	public CustomerMaster getClone() {
		try {
			return (CustomerMaster) super.clone();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public final int getCustomerid() {
		return customerid;
	}

	public final void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public final String getCustomerName() {
		return customerName;
	}

	public final void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public final String getCustomerAddress() {
		return customerAddress;
	}

	public final void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public final String getCustomerPhone() {
		return customerPhone;
	}

	public final void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	public final User getUser() {
		return user;
	}
	public final void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "CustomerMaster [customerid=" + customerid + ", customerName=" + customerName + ", customerAddress=" + customerAddress
				+ ", customerPhone=" + customerPhone + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerAddress == null) ? 0 : customerAddress.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((customerPhone == null) ? 0 : customerPhone.hashCode());
		result = prime * result + customerid;
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
		CustomerMaster other = (CustomerMaster) obj;
		if (customerAddress == null) {
			if (other.customerAddress != null)
				return false;
		} else if (!customerAddress.equals(other.customerAddress))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (customerPhone == null) {
			if (other.customerPhone != null)
				return false;
		} else if (!customerPhone.equals(other.customerPhone))
			return false;
		if (customerid != other.customerid)
			return false;
		return true;
	}

	@Override
	public int compareTo(CustomerMaster o) {
		return this.customerName.compareTo(o.customerName);
	}
	
}
