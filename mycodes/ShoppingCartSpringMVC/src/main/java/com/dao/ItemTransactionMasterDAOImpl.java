package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.ItemTransactionMaster;

@Repository
public class ItemTransactionMasterDAOImpl implements ItemTransactionMasterDAO{
	@Autowired
	private SessionFactory factory;
	
	public final SessionFactory getFactory() {
		return factory;
	}
	public final void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	@Override
	public void createItemTransaction(ItemTransactionMaster itemTransactionMaster) {
		Session session=factory.getCurrentSession();
		session.persist(itemTransactionMaster);
	}
	
}
