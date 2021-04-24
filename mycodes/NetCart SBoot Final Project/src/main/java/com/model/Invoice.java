package com.model;

import java.util.Arrays;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

import java.io.Serializable;
import java.sql.Date;
@Entity
public class Invoice implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int invno;
	private Date invdate;
	private int customerno;
	@Lob
	@Column(name = "itemqtymap", columnDefinition = "LONGBLOB")
	private byte[] itemqtymap;
	@Transient
	private HashMap<Items,Integer> itemquantitymap;
	
	
	
	public int getInvno() {
		return invno;
	}
	public void setInvno(int invno) {
		this.invno = invno;
	}
	public Date getInvdate() {
		return invdate;
	}
	public void setInvdate(Date invdate) {
		this.invdate = invdate;
	}
	public int getCustomerno() {
		return customerno;
	}
	public void setCustomerno(int customerno) {
		this.customerno = customerno;
	}
	public byte[] getItemqtymap() {
		return itemqtymap;
	}
	public void setItemqtymap(byte[] itemqtymap) {
		this.itemqtymap = itemqtymap;
	}
    
	public HashMap<Items, Integer> getItemquantitymap() {
		return itemquantitymap;
	}
	public void setItemquantitymap(HashMap<Items, Integer> itemquantitymap) {
		this.itemquantitymap = itemquantitymap;
	}

	

	@Override
	public String toString() {
		return "Invoice [invno=" + invno + ", invdate=" + invdate + ", customerno=" + customerno + ", itemqtymap="
				+ Arrays.toString(itemqtymap) + ", itemquantitymap=" + itemquantitymap + "]";
	}
	
	
	
}
