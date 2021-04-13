package inheritancedemo;

import org.hibernate.Session;

import utility.HibernateUtility;

public class InheritanceClient {
	public static void main(String[] args) {
		Session session=HibernateUtility.getSession();
		
		ShoeFactory factory=new ShoeFactory();
		factory.setName("parent name....");
		
		BataShoeFactory bsf=new BataShoeFactory();
		bsf.setName("parent name bata bata bata.....");
		bsf.setBataName("bata bata bata bata........................");
		
		LakhaniShoeFactory lsf=new LakhaniShoeFactory();
		lsf.setName("parent name lakki lakki lakki.....");
		lsf.setLakhaniName("lakhani lakhani lakhani.................");
		
		session.save(factory);
		session.save(bsf);
		session.save(lsf);
		
		session.beginTransaction().commit();
	}
}
