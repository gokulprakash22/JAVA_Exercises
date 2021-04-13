package querydemo;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import onetomanydemo.Address;
import onetomanydemo.Employee;
import utility.HibernateUtility;
public class QueryClient2 {
	public static void main(String[] args) {
		QueryClient2 obj=new QueryClient2();
		obj.query8();
	}
	public static void display(Criteria criteria)
	{
		List<Employee> employees=criteria.list();
		Iterator<Employee> iter=employees.iterator();
		while(iter.hasNext())
		{
			System.out.println(iter.next().getEmpname());
		}
	}
	public void query1() {
		Session session=HibernateUtility.getSession();
		Criteria criteria=session.createCriteria(Employee.class);
		display(criteria);
	}
	public void query11()
	{
		Session session=HibernateUtility.getSession();
		Criteria criteria=session.createCriteria(Employee.class);
		criteria.addOrder(Order.desc("empname"));
		display(criteria);
	}
	public void query2()
	{
		Session session=HibernateUtility.getSession();
		Criteria criteria=session.createCriteria(Employee.class);
		//criteria.add(Restrictions.eq("name", "ramu"));
		//or
		criteria.add(Property.forName("empname").eq("ramu"));
		Employee emp=(Employee)criteria.uniqueResult();
		System.out.println(emp.getEmpname());
	}
	public void query3()
	{
		Session session=HibernateUtility.getSession();
		Criteria criteria=session.createCriteria(Address.class);
		criteria.add(Restrictions.eq("emp.empname", "ramu"));
		List<Address> addresses=criteria.list();
		Iterator<Address> iter=addresses.iterator();
		while(iter.hasNext())
		{
			System.out.println(iter.next().getCity());
		}
	}
	public void query4()
	{
		Session session=HibernateUtility.getSession();
		int f[]={new Integer(20),new Integer(40)};
		Criteria criteria=session.createCriteria(Employee.class);
		criteria.add(Restrictions.between("empsal",0,25000));
		display(criteria);
	}
	public void query5()
	{
		Session session=HibernateUtility.getSession();
		Criteria criteria=session.createCriteria(Employee.class);
		criteria.add(Restrictions.like("empname", "R%").ignoreCase());
		display(criteria);	
	}
	public void query6()
	{
		Session session=HibernateUtility.getSession();
		Criteria criteria=session.createCriteria(Employee.class);
		criteria.
			add(Restrictions.like("empname", "R%").ignoreCase())
			.add(Restrictions.gt("empsal", 10000));
		display(criteria);
	}
	public void query7()
	{
		Session session=HibernateUtility.getSession();
		Criteria criteria=session.createCriteria(Employee.class);
		criteria.add(
				Restrictions.or(Restrictions.like("empname", "R%")
						.ignoreCase(),
				Restrictions.gt("empsal",30000)));
				display(criteria);
	}
	public void query8()
	{
		Session session=HibernateUtility.getSession();
		DetachedCriteria dc=DetachedCriteria.forClass(Employee.class);
		dc.add(
				Restrictions.or(Restrictions.like("empname", "R%"),
				Restrictions.gt("empsal",10000))
			);
		Criteria criteria=dc.getExecutableCriteria(session);
		display(criteria);
	}
}