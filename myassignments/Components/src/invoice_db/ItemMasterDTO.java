package invoice_db;

import java.io.Serializable;

public class ItemMasterDTO implements Serializable{
	private int itemno;
	private String itemdescription;
	private int itemprice;
	private String itemunit;
	public final int getItemno() {
		return itemno;
	}
	public final void setItemno(int itemno) {
		this.itemno = itemno;
	}
	public final String getItemdescription() {
		return itemdescription;
	}
	public final void setItemdescription(String itemdescription) {
		this.itemdescription = itemdescription;
	}
	public final int getItemprice() {
		return itemprice;
	}
	public final void setItemprice(int itemprice) {
		this.itemprice = itemprice;
	}
	public final String getItemunit() {
		return itemunit;
	}
	public final void setItemunit(String itemunit) {
		this.itemunit = itemunit;
	}
	@Override
	public String toString() {
		return "ItemMasterDTO [itemno=" + itemno + ", itemdescription=" + itemdescription + ", itemprice=" + itemprice
				+ ", itemunit=" + itemunit + "]";
	}
	
	
}
