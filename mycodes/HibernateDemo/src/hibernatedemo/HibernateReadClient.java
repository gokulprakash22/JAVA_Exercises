package hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateReadClient {
	public static void main(String[] args) {
				
		//Step1 - Load Configuration
		Configuration cfg=new Configuration();

		//Step 2 - Build SessionFactory
		SessionFactory factory=cfg.configure().buildSessionFactory();
		
		//Step 3 - Create a session (Local Cache - Client specific)
		Session session=factory.openSession();
		
		//Create a HibernateUtility class with ThreadLocal to get a unique session for every thread.-----class work
		
		//To make the DTO hibernate aware, ensure that you call save method of session with the DTO object.
		
		BookDTO bookObj=(BookDTO)session.get(BookDTO.class, Integer.valueOf(1));
		
        System.out.println(bookObj);
		
		Comment comm=bookObj.getComment();
		
		System.out.println(comm.getMsg());
		
	}
}
