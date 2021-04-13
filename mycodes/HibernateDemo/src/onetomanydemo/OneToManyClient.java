package onetomanydemo;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import utility.HibernateUtility;

public class OneToManyClient {
	public static void main(String[] args) {
		Session session=HibernateUtility.getSession();
		
		Employee emp=new Employee();
		emp.setEmpname("ramu");
		emp.setEmpsal(20000);
		
		Address add1=new Address();
		add1.setCity("chennai");
		add1.setStreet("raja street");
		add1.setEmp(emp);
		Address add2=new Address();
		add2.setCity("banglore");
		add2.setStreet("mg street");
		add2.setEmp(emp);
		
		Set<Address> addresses=new HashSet<Address>();
		addresses.add(add1);
		addresses.add(add2);
		
		emp.setAddresses(addresses);
		session.save(emp);
		//session.save(add1);
		//session.save(add2);
		
		HibernateUtility.closeSession(null);
		
	}
}
