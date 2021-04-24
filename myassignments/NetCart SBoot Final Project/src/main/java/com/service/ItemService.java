package com.service;

import java.util.List;

import com.model.Items;

public interface ItemService {
	public List<Items> loadAllItems();
	public List<Items> loadAllItemsByCategory(String category);
	public void deleteItembyItemno(int itemno);
	public void updateItem(Items item);
	public void insertItem(Items item);
	public Items loadItembyItemno(int itemno);
}
