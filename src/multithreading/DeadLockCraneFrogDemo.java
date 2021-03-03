package multithreading;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadLockCraneFrogDemo {
	public static void main(String[] args) {
		 ExecutorService es=Executors.newFixedThreadPool(2);
		 Crane crane=new Crane();
		 Frog frog=new Frog();
		 es.execute(()->{
			 crane.eat(frog);
		 });
		 es.execute(()->{
			 frog.escape(crane);
		 });
		 es.shutdown();
	 }
}
class Crane{
	synchronized public void eat(Frog frog) {
		 //try{Thread.sleep(5000);}catch(Exception e) {}
		 //System.out.println(" ");
		 frog.leaveCrane();
		 System.out.println("swaha.....");
	}
	synchronized public void spitFrog() {
	
	}
}
class Frog{
	synchronized public void escape(Crane crane) {
		crane.spitFrog();
	}
	synchronized public void leaveCrane() {
	
	}
}