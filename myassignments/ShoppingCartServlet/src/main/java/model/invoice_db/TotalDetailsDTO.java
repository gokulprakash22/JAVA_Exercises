package model.invoice_db;

import java.util.List;
import java.util.Set;

public class TotalDetailsDTO {
String invdate;
CustomerMasterDTO customermasterdto;
Set<ItemDetailsDTO> itemdetails;
public String getInvdate() {
	return invdate;
}
public void setInvdate(String invdate) {
	this.invdate = invdate;
}
public CustomerMasterDTO getCustomermasterdto() {
	return customermasterdto;
}
public void setCustomemasterdto(CustomerMasterDTO customemasterdto) {
	this.customermasterdto = customemasterdto;
}
public Set<ItemDetailsDTO> getItemdetails() {
	return itemdetails;
}
public void setItemdetails(Set<ItemDetailsDTO> itemdetails) {
	this.itemdetails = itemdetails;
}
@Override
public String toString() {
	return "TotalDetails [invdate=" + invdate + ", customemasterdto=" + customermasterdto + ", itemdetails="
			+ itemdetails + "]";
}

}
