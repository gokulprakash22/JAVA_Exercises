package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.CustomerMaster;

@Repository
public class CustomerMasterDAOImpl implements CustomerMasterDAO{
	@Autowired
	private SessionFactory factory;
	
	public final SessionFactory getFactory() {
		return factory;
	}
	public final void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	@Override
	public void createCustomer(CustomerMaster customerMaster) {
		Session session=factory.getCurrentSession();
		session.persist(customerMaster);
	}
	@Override
	public CustomerMaster getCustomerMaster(int customerid) {
		Session session=factory.getCurrentSession();
		CustomerMaster customerMaster = (CustomerMaster) session.get(CustomerMaster.class, customerid);
		return customerMaster;
	}
}
