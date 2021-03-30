package model.invoice_db;

import java.util.Set;

public interface ItemMasterDAO {
	public int insertItemDetails(ItemMasterDTO itemMasterDTO);
	public int deleteItemDetails(int itemno);
	public int updateItemDetails(ItemMasterDTO itemMasterDTO);
	public ItemMasterDTO getItemMaster(int itemno);
	public Set<ItemMasterDTO> getItemMasterAll();
	public Set<ItemMasterDTO> getItemMasterAll(String category);
}
