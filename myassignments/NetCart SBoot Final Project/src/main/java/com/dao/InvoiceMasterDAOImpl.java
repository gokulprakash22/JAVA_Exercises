package com.dao;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Invoice;
import com.model.Items;

@Repository
public class InvoiceMasterDAOImpl implements InvoiceMasterDAO {
	@Autowired
	private SessionFactory factory;
	public final SessionFactory getFactory() {
		return factory;
	}
	public final void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public int insertInvoice(Invoice invMasterDTO) {
		Session session=factory.getCurrentSession();
		System.out.println(invMasterDTO);
		session.save(invMasterDTO);
		session.flush();
		int invno= invMasterDTO.getInvno();
		System.out.println("Invoice No "+invno);
		return invno;
	}

	@Override
	public int deleteInvoice(int invno) {
		Session session=factory.getCurrentSession();
	    Query query = session.createQuery("DELETE from Invoice where invno = :invno");
	    query.setParameter("invno", invno);
	     int row = query.executeUpdate();
		return row;
	}
	
	@Override
	public Invoice getInvoiceMaster(int invno) {
		Session session=factory.getCurrentSession();
		Query query=session.createQuery("from Invoice where invno = :invno");
		query.setParameter("invno", invno);
		Invoice invoice = (Invoice) query.uniqueResult();
		return invoice;
	}
	@Override
	public List<Invoice> getInvoiceMasterAll() {
		Session session=factory.getCurrentSession();
		Query query=session.createQuery("from Invoice");
		List<Invoice> invoicelist = (List<Invoice>) query.list();
		return invoicelist;
	}

	@Override
	public String getDate(int invno) {
		Session session=factory.getCurrentSession();
		Query query=session.createQuery("select invdate from Invoice where invno = :invno");
		query.setParameter("invno", invno);
		String date= (String) query.uniqueResult();
		return date;
	}
	
	@Override
	public int updateInvoice(Invoice invMasterDTO) {
		try {
			Session session=factory.getCurrentSession();
			session.update(invMasterDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int getCustno(int invno) {
		Session session=factory.getCurrentSession();
		Query query=session.createQuery("select customerno from Invoice where invno = :invno");
		query.setParameter("invno", invno);
		int custno= (Integer) query.uniqueResult();
		return custno;
	}
	@Override
	public byte[] getInvoiceMapByInvNo(int invno) {
		Session session=factory.getCurrentSession();
		Query query=session.createQuery("SELECT itemqtymap from Invoice where invno = :invno");
		query.setParameter("invno", invno);
		System.out.println(invno);
		System.out.println(query);
		try {
		  byte[] st = (byte[]) query.uniqueResult();
	      ByteArrayInputStream baip = new ByteArrayInputStream(st);
	      ObjectInputStream ois = new ObjectInputStream(baip);
	      HashMap<Items,Integer> invoice = (HashMap<Items,Integer>) ois.readObject();
		System.out.println("Invoice Result "+invoice);
		return st;
		}catch(Exception e) {}

		return null;
	}
	@Override
	public List<Invoice> getInvoiceByCustomerNo(int custno) {
		Session session=factory.getCurrentSession();
		Query query=session.createQuery("from Invoice where customerno = :customerno");
		query.setParameter("customerno", custno);
		List<Invoice> invoicelist = (List<Invoice>) query.list();
		return invoicelist;
	}
	@Override
	public List<Invoice> getInvoiceByInvDate(String startdate,String enddate) {
		Session session=factory.getCurrentSession();
		Query query=session.createQuery("from Invoice WHERE invdate BETWEEN '"+startdate+"' and '"+enddate+"'");
		List<Invoice> invoicelist = (List<Invoice>) query.list();
		return invoicelist;
	}

}