package annoinheritancedemo;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class Client {
public static void main(String[] args) {
	SessionFactory factory = 
		new AnnotationConfiguration().configure("hibernate.anno.cfg.xml").buildSessionFactory();
	
	Session session=factory.openSession();
	Transaction tx=session.beginTransaction();

	Maruti maruti=new Maruti();
	maruti.setAutofeature("maruti auto features");
	maruti.setCarfeatures("maruti car features");
	maruti.setMarutifeatures("maruti features");
	
	session.save(maruti);
	
	Car car=new Car();
	car.setAutofeature("car auto features");
	car.setCarfeatures("car features");
	
	session.save(car);
	tx.commit();
	
	Query q=session.createQuery("select a from auto a");
	List l=q.list();
	Iterator i=l.iterator();
	while(i.hasNext())
	{
		Automobile a=(Automobile)i.next();
		a.handle();
	}	
}
public void display()
{
//select r from Automobile a	
}

}
/**

none
No action will be performed.

create-only
Database creation will be generated.

drop
Database dropping will be generated.

create
Database dropping will be generated followed by database creation.

create-drop
Drop the schema and recreate it on SessionFactory startup. Additionally, drop the schema on SessionFactory shutdown.

validate
Validate the database schema

update
Update the database schema
**/
