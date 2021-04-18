package com.dao;

import java.util.List;

import com.model.ItemMaster;

public interface ItemMasterDAO {
	public void createItem(ItemMaster itemMaster);
	public void updateItem(ItemMaster itemMaster);
	public void deleteItem(ItemMaster itemMaster);
	public ItemMaster getItemMaster(int itemid);
	public List<ItemMaster> getItemsByCategory(String category);
	public List<ItemMaster> getAllItems();
}
