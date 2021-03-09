package mypac;

public class LambdaDemo {
	public static void main(String[] args) {
		 display(new MyInterImpl());
		 display(new MyInter() {
			 @Override
			 public void met() {
				 System.out.println("Anonymous my inter interface met method called....");
			 }
			// @Override
			// public void met2() {
			// // TODO Auto-generated method stub
			//
			// }
		 });
		 //The below syntax of lambda is possible only on functional interfaces
		 //function interface - a interface with only one method...
		 display(()->{System.out.println("Lambda - my interface met method called...");});
		 display2((name,i)->{System.out.println(name+":"+i);});
		 String result=displayReturn((name,i)->{
			 name=name+"one more hello world";
			 return name+":"+i;
		 });
		 System.out.println("The result is...:"+result);
	 }
	
	public static void display(MyInter myinter) {
		myinter.met();
	}
	public static void display2(MyInter2 myinter2) {
		myinter2.met2("hello world",11);
	}
	public static String displayReturn(MyInter3 myinter3) {
		return myinter3.met3("hello world......", 1000);
	}
}
interface MyInter3{
	public String met3(String s,int i);
}
interface MyInter2{
	public void met2(String s,int i);
}
@FunctionalInterface
interface MyInter{
	public void met();
	//public void met2();
}
class MyInterImpl implements MyInter{
	@Override
	public void met() {
		System.out.println("outer implementation - interface method is called...");
	}
	// @Override
	// public void met2() {
	// // TODO Auto-generated method stub
	//
	// }
}
