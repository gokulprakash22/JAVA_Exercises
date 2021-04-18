package com.service;

import java.util.List;

import com.model.ItemMaster;

public interface AdminService {
	public void createItem(ItemMaster itemMaster);
	public List<ItemMaster> getAllItems();
	public ItemMaster getitem(int itemid);
	public void updateItem(ItemMaster itemMaster);
	public void deleteItem(int itemid);
}
