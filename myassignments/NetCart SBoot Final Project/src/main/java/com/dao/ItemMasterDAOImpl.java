package com.dao;

import org.hibernate.query.Query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Invoice;
import com.model.Items;

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
	public int insertItemDetails(Items itemMasterDTO) {
		try {
			Session session=factory.getCurrentSession();
			session.persist(itemMasterDTO);
			return 0;
		}catch(Exception e) {}
		return 0;
	}

	@Override
	public int deleteItemDetails(int itemno) {
		Session session=factory.getCurrentSession();
	    Query query = session.createQuery("DELETE from Items where itemno = :itemno");
	    query.setParameter("itemno", itemno);
	    int row = query.executeUpdate();
		return row;
		
	}


	@Override
	public Items getItemMaster(int itemno) {
		Session session=factory.getCurrentSession();
		Query query=session.createQuery("from Items where itemno = :itemno");
		query.setParameter("itemno", itemno);
		Items item = (Items) query.uniqueResult();
		return item;
	
	}

	@Override
	public List<Items> getItemMasterAll(String category) {
		Session session=factory.getCurrentSession();
		Query query=session.createQuery("from Items where category = :category");
		query.setParameter("category", category);
		List<Items> itemlist = query.list();
		System.out.println(itemlist);
		return itemlist;
	
	}
	@Override
	public List<Items> getItemMasterAllItems() {
		Session session=factory.getCurrentSession();
		Query query=session.createQuery("from Items");
		List<Items> itemlist = query.list();
		System.out.println(itemlist);
		return itemlist;
	}
	
	@Override
	public int getStockByItemno(int itemno) {
		Session session=factory.getCurrentSession();
		Query query=session.createQuery("select stock from Items where itemno = :itemno");
		query.setParameter("itemno", itemno);
		int itemstock=(Integer)query.uniqueResult();
		return itemstock;
	}
	@Override
	public void decreaseStock(int itemno, int qty) {
		Session session=factory.getCurrentSession();
		Query query=session.createQuery("update Items i set i.stock=:qty where i.itemno=:itemno");
		query.setParameter("qty", getStockByItemno(itemno)-qty);
		query.setParameter("itemno",itemno);
		query.executeUpdate();
		
	}
	@Override
	public void updateItem(Items item) {
		try {
			Session session=factory.getCurrentSession();
			session.update(item);
		} catch (Exception e) {
			e.printStackTrace();
	
		}
	}
	@Override
	public int getNoofItems() {
		Session session=factory.getCurrentSession();
		Query query=session.createQuery("select count(i) from Items i");
		Long count=(Long)query.uniqueResult();
		System.out.println("Count "+count);
		return count.intValue();
	}

	

}
