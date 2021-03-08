package yourpack;

import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObserverDemoFireAlarm {
	public static void main(String[] args) {
		FireAlarm alarm = new FireAlarm();
		Student student = new Student();
		Teacher teacher = new Teacher();
		
		alarm.addObserver(student);
		alarm.addObserver(teacher);
		alarm.setAlarm();
	}
}

class MultiThreadedObservable extends Observable  {
	Vector<Observer> vector = new Vector<>();
	
	@Override
	public synchronized void addObserver(Observer o) {
		vector.add(o);
	}
		
	@Override
	public void notifyObservers(Object arg) {
		ExecutorService es = Executors.newFixedThreadPool(vector.size());
		Enumeration<Observer> en = vector.elements();
		
		while(en.hasMoreElements()) {
			Observer o = en.nextElement();
			es.execute(() -> {o.update(this, arg);});
		}
		
		es.shutdown();
	}
	
}

class FireAlarm extends MultiThreadedObservable {
	public void setAlarm() {
		setChanged();
		notifyObservers("Fire");
	}
}

class Teacher implements Observer {
	public Teacher() {
		
	}
	public void run(String msg) {
		try {Thread.sleep(10000);}catch(Exception e) {}
		System.out.println(msg+".....Teacher is running............");
	}

	@Override
	public void update(Observable o, Object arg) {
		run((String)arg);
	}
}

class Student implements Observer {
	public Student() {
		
	}
	public void run(String msg) {
		System.out.println(msg+".....Student is running............");
	}

	@Override
	public void update(Observable o, Object arg) {
		run((String)arg);
	}
}