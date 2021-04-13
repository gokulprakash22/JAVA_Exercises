package compkeydemo;

import org.hibernate.Session;

import utility.HibernateUtility;

public class CompKeyClient {
	public static void main(String[] args) {
	Session session=HibernateUtility.getSession();
	CompKey compkey=new CompKey();
	Invoice invoice=new Invoice();
	compkey.setInv_id(101);
	compkey.setItem_id(1001);
	invoice.setCompkey(compkey);
	invoice.setQuantity(5);
	
	session.save(invoice);
	HibernateUtility.closeSession(null);
	}
}
