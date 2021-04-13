package hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateClient {
	public static void main(String[] args) {
		BookDTO bookDto=new BookDTO(1,"java","james",340);
		
		Comment comment=new Comment();
		comment.setMsg("this is comment value.................");
		
		bookDto.setComment(comment);
		
		//Step1 - Load Configuration
		Configuration cfg=new Configuration();

		//Step 2 - Build SessionFactory
		SessionFactory factory=cfg.configure().buildSessionFactory();
		
		//Step 3 - Create a session (Local Cache - Client specific)
		Session session=factory.openSession();
		
		//Create a HibernateUtility class with ThreadLocal to get a unique session for every thread.-----class work
		
		//To make the DTO hibernate aware, ensure that you call save method of session with the DTO object.
		session.save(bookDto);
		
		session.beginTransaction().commit();
		
		
		
	}
}
