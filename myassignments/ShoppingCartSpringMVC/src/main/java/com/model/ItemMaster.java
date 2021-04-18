package com.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ItemMaster implements Comparable<ItemMaster>, Cloneable, Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemid;
	
	private String itemName;
	private String itemUnit;
	private int itemPrice;
	private String itemCategory;
	private String imageURL;
	private int itemStock;
	
	public ItemMaster() {
		// TODO Auto-generated constructor stub
	}
	
	public ItemMaster(int itemid, String itemName, String itemUnit, int itemPrice, String itemCategory, String imageURL, int itemStock) {
		super();
		this.itemid = itemid;
		this.itemName = itemName;
		this.itemUnit = itemUnit;
		this.itemPrice = itemPrice;
		this.itemCategory = itemCategory;
		this.imageURL = imageURL;
		this.itemStock = itemStock;
	}

	public ItemMaster getClone() {
		try {
			return (ItemMaster) super.clone();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public final int getItemid() {
		return itemid;
	}

	public final void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public final String getItemName() {
		return itemName;
	}

	public final void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public final String getItemUnit() {
		return itemUnit;
	}

	public final void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	
	public final int getItemPrice() {
		return itemPrice;
	}

	public final void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public final String getItemCategory() {
		return itemCategory;
	}

	public final void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public final String getImageURL() {
		return imageURL;
	}

	public final void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public final int getItemStock() {
		return itemStock;
	}

	public final void setItemStock(int itemStock) {
		this.itemStock = itemStock;
	}

	@Override
	public String toString() {
		return "ItemMaster [itemid=" + itemid + ", itemName=" + itemName + ", itemUnit=" + itemUnit + ", itemPrice="
				+ itemPrice + ", itemCategory=" + itemCategory + ", imageURL=" + imageURL + ", itemStock=" + itemStock
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imageURL == null) ? 0 : imageURL.hashCode());
		result = prime * result + ((itemCategory == null) ? 0 : itemCategory.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + itemPrice;
		result = prime * result + itemStock;
		result = prime * result + ((itemUnit == null) ? 0 : itemUnit.hashCode());
		result = prime * result + itemid;
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
		ItemMaster other = (ItemMaster) obj;
		if (imageURL == null) {
			if (other.imageURL != null)
				return false;
		} else if (!imageURL.equals(other.imageURL))
			return false;
		if (itemCategory == null) {
			if (other.itemCategory != null)
				return false;
		} else if (!itemCategory.equals(other.itemCategory))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemPrice != other.itemPrice)
			return false;
		if (itemStock != other.itemStock)
			return false;
		if (itemUnit == null) {
			if (other.itemUnit != null)
				return false;
		} else if (!itemUnit.equals(other.itemUnit))
			return false;
		if (itemid != other.itemid)
			return false;
		return true;
	}
	
	@Override
	public int compareTo(ItemMaster o) {
		return this.itemName.compareTo(o.itemName);
	}
	
}
