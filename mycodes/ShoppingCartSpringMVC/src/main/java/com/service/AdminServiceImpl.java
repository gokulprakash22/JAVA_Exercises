package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ItemMasterDAO;
import com.model.ItemMaster;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	@Autowired
	private ItemMasterDAO itemMasterDAO;
	
	public final ItemMasterDAO getItemMasterDAO() {
		return itemMasterDAO;
	}
	public final void setItemMasterDAO(ItemMasterDAO itemMasterDAO) {
		this.itemMasterDAO = itemMasterDAO;
	}
	@Override
	public void createItem(ItemMaster itemMaster) {
		itemMasterDAO.createItem(itemMaster);
	}
	@Override
	public List<ItemMaster> getAllItems() {
		return itemMasterDAO.getAllItems();
	}
	@Override
	public ItemMaster getitem(int itemid) {
		return itemMasterDAO.getItemMaster(itemid);
	}
	@Override
	public void updateItem(ItemMaster itemMaster) {
		itemMasterDAO.updateItem(itemMaster);
	}
	@Override
	public void deleteItem(int itemid) {
		ItemMaster itemMaster = itemMasterDAO.getItemMaster(itemid);
		itemMasterDAO.deleteItem(itemMaster);
	}
	
}
