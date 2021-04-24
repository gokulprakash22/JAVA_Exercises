package com.dao;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Customer;	

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
	public int registerUser(Customer customer) {
		Session session=factory.getCurrentSession();
		session.save(customer);
		return 0;
		
	}
	@Override
	public Customer getCustomerMaster(int custno) {
		Session session=factory.getCurrentSession();
		Query query=session.createQuery("from Customer where customerno = :customerno");
		query.setParameter("customerno", custno);
		Customer customer= (Customer) query.uniqueResult();
		return customer;
	}
	
	@Override
	public Customer getCustomerMaster(String uname,String upass) {
		Session session=factory.getCurrentSession();
		Query query=session.createQuery("from Customer cust where cust.user.uname=:uname and cust.user.upass=:upass");
		query.setParameter("uname", uname);
		query.setParameter("upass", upass);
		return (Customer)query.uniqueResult();
	}

	@Override
	public int updateCustomer(Customer customerMasterDTO) {
		Session session=factory.getCurrentSession();
		session.update(customerMasterDTO);
		return 0;
	}
//	@Override
//	public int getcustNo(String uname,String upass) {
//		Session session=factory.getCurrentSession();
//		Query query=session.createQuery("select cust.customerno from Customer as cust where cust.customerno in(SELECT uid from Users as user where user.id=cust.customerno and uname=:uname and upass = :upass)");
//		query.setParameter("uname", uname);
//		query.setParameter("upass", upass);
//		int custno= (Integer) query.uniqueResult();
//		return custno;
//	}

}
