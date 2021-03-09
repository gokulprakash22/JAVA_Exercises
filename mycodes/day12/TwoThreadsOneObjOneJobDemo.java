package multithreading;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TwoThreadsOneObjOneJobDemo {
	public static void main(String[] args) {
		 ExecutorService es=Executors.newFixedThreadPool(2);
		 Gun bofors=new Gun();
		 es.execute(()->{
			 for(int i=0;i<5;i++) {
				 bofors.fillGun();
			 }
		 });
		 es.execute(()->{
			 for(int i=0;i<5;i++) {
				 bofors.shootGun();
			 } 
		 });
		 es.shutdown();
	 }
}
class Gun{
	boolean flag;
	synchronized public void fillGun() {
		 if(flag) {
			 try{wait();}catch(Exception e) {}
		 }
		 System.out.println("I will fill the gun..........................");
		 flag=true;
		 notify();
	 }
	
	synchronized public void shootGun() {
		 if(!flag) {
			 try {wait();}catch(Exception e) {}
		 }
		 System.out.println("I will shoot the gun........");
		 flag=false;
		 notify();
	 }
}
