package manytomanydemo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import utility.HibernateUtility;

public class ManyToManyClient {
public static void main(String[] args) {
	Session session=HibernateUtility.getSession();
	Student student=new Student();
	student.setSname("Gokul");
	student.setMarks(500);
	
	Training training1=new Training();
	training1.setTname("JAVA");
	LocalDate date1 = LocalDate.now();
	training1.setTstartdate(date1);
	
	Training training2=new Training();
	training2.setTname("Python");
	LocalDate date2 = LocalDate.now().plusDays(10);
	training2.setTstartdate(date2);
	
	Set<Training> trainingset=new HashSet<Training>();
	
	trainingset.add(training1);
	trainingset.add(training2);
	student.setTrainings(trainingset);
	
	session.save(student);
	session.save(training1);
	session.save(training2);
	HibernateUtility.closeSession(null);
}
}
