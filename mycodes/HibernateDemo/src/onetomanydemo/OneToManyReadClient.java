package onetomanydemo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;

import utility.HibernateUtility;

public class OneToManyReadClient {
	public static void main(String[] args) {
		Session session = HibernateUtility.getSession();
		
		Employee emp = (Employee) session.get(Employee.class, Integer.valueOf(1));
		
		System.out.println(emp.getEmpname());
		System.out.println(emp.getEmpsal());
		Set<Address> set = emp.getAddresses();
		Iterator<Address> iterator = set.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		HibernateUtility.closeSession(null);
	}
}