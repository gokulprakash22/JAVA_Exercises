package service;

import java.util.Set;

import model.invoice_db.CustomerMasterDTO;
import model.invoice_db.ItemDetailsDTO;
import model.invoice_db.ItemMasterDTO;
import model.invoice_db.ItemTransactionMasterDTO;
import model.invoice_db.TotalDetailsDTO;

public interface ShoppingService {
	public Set<ItemMasterDTO> loadAllItems(String shopid);
	public int getInvNo(int custno,String dateinstring);
	public String getInvdate(int custno);
	public Set<ItemMasterDTO> buyItems(Set<ItemTransactionMasterDTO> itemtransactionmasterdtoset);
	public CustomerMasterDTO getCustomerDetails(int customerno);
	public Set<ItemDetailsDTO> getItemDetails(int invno);
}