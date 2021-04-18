package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.ItemMaster;

@Repository
public class ItemMasterDAOImpl implements ItemMasterDAO{
	@Autowired
	private SessionFactory factory;
	
	public final SessionFactory getFactory() {
		return factory;
	}
	public final void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public void createItem(ItemMaster itemMaster) {
		Session session=factory.getCurrentSession();
		session.persist(itemMaster);
	}
	@Override
	public void updateItem(ItemMaster itemMaster) {
		Session session=factory.getCurrentSession();
		session.update(itemMaster);
	}
	@Override
	public void deleteItem(ItemMaster itemMaster) {
		Session session=factory.getCurrentSession();
		session.delete(itemMaster);
	}
	@Override
	public ItemMaster getItemMaster(int itemid) {
		Session session=factory.getCurrentSession();
		ItemMaster itemMaster = (ItemMaster) session.get(ItemMaster.class, itemid);
		return itemMaster;
	}
	@Override
	public List<ItemMaster> getItemsByCategory(String category) {
		Session session=factory.getCurrentSession();
		Query query= session.createQuery("from ItemMaster where itemCategory=:category");
		query.setParameter("category", category);
		List<ItemMaster> itemMasterList = (List<ItemMaster>) query.list();
		return itemMasterList;
	}
	@Override
	public List<ItemMaster> getAllItems() {
		Session session=factory.getCurrentSession();
		Query query= session.createQuery("from ItemMaster");
		List<ItemMaster> itemMasterList = (List<ItemMaster>) query.list();
		return itemMasterList;
	}
	
}
