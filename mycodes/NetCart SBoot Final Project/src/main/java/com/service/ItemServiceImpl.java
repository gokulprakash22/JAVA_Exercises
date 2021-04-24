package com.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ItemMasterDAO;
import com.model.Items;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{
	@Autowired
	private ItemMasterDAO itemDao;
	@Override
	public List<Items> loadAllItemsByCategory(String category) {
		List<Items> itemmasterdtoset=new ArrayList<Items>();
		itemmasterdtoset=itemDao.getItemMasterAll(category);
		return itemmasterdtoset;
	}
	@Override
	public void deleteItembyItemno(int itemno) {
		itemDao.deleteItemDetails(itemno);
		
	}
	@Override
	public void updateItem(Items item) {
		itemDao.updateItem(item);
		
	}
	@Override
	public void insertItem(Items item) {
		itemDao.insertItemDetails(item);
		
	}
	@Override
	public List<Items> loadAllItems() {
		return itemDao.getItemMasterAllItems();
	}

	@Override
	public Items loadItembyItemno(int itemno) {
		return itemDao.getItemMaster(itemno);
	}
}
