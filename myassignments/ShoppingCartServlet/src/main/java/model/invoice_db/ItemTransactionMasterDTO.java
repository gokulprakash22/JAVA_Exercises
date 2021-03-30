package model.invoice_db;

import java.io.Serializable;

public class ItemTransactionMasterDTO implements Serializable {
private int invno;
private int itemno;
private int itemqty;
public final int getInvno() {
	return invno;
}
public final void setInvno(int invno) {
	this.invno = invno;
}
public final int getItemno() {
	return itemno;
}
public final void setItemno(int itemno) {
	this.itemno = itemno;
}
public final int getItemqty() {
	return itemqty;
}
public final void setItemqty(int itemqty) {
	this.itemqty = itemqty;
}
@Override
public String toString() {
	return "ItemTransactionDTO [invno=" + invno + ", itemno=" + itemno + ", itemunit=" + itemqty + "]";
}

}
