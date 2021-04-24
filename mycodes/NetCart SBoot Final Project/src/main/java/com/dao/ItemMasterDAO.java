package com.dao;

import java.util.List;


import com.model.Items;

public interface ItemMasterDAO {
	public int insertItemDetails(Items itemMasterDTO);
	public int deleteItemDetails(int itemno);
	public void updateItem(Items item);
	public Items getItemMaster(int itemno);
	public List<Items> getItemMasterAll(String category);
	public void decreaseStock(int itemno,int itemqty);
	public int getStockByItemno(int itemno);
	public List<Items> getItemMasterAllItems();
	public int getNoofItems();
}
