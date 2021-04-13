package compkeydemo;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
public class Invoice {
	private CompKey compkey;
	private int quantity;
	public CompKey getCompkey() {
		return compkey;
	}
	public void setCompkey(CompKey compkey) {
		this.compkey = compkey;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}