package com.dao;

import java.io.ByteArrayInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.sql.Blob;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.CustomerMaster;
import com.model.InvoiceMaster;
import com.model.TotalDetails;


@Repository
public class invoiceMasterDAOImpl implements InvoiceMasterDAO{

	@Autowired
	private SessionFactory factory;
	
	public final SessionFactory getFactory() {
		return factory;
	}
	public final void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public InvoiceMaster createInvoice(InvoiceMaster invoiceMaster) {
		Session session=factory.getCurrentSession();
		session.persist(invoiceMaster);
		return invoiceMaster;
		
	}
	@Override
	public void updateInvoice(InvoiceMaster invoiceMaster) {
		Session session=factory.getCurrentSession();
		session.update(invoiceMaster);
	}
	@Override
	public InvoiceMaster getInvoiceMaster(int invid) {
		Session session=factory.getCurrentSession();
		InvoiceMaster invoiceMaster = (InvoiceMaster) session.get(InvoiceMaster.class, invid);
		return invoiceMaster;
	}
	@Override
	public TotalDetails getTotalDetails(int invid) {
		Session session=factory.getCurrentSession();
		InvoiceMaster invoiceMaster = (InvoiceMaster) session.get(InvoiceMaster.class, invid);
		Blob blob = invoiceMaster.getTotalDetails();
		TotalDetails totalDetails = null;
		try {
			byte[] b = blob.getBytes(1, (int)blob.length());
			ByteArrayInputStream bis = new ByteArrayInputStream(b);
			ObjectInput in = new ObjectInputStream(bis);
			totalDetails = (TotalDetails) in.readObject();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return totalDetails;
	}

}
