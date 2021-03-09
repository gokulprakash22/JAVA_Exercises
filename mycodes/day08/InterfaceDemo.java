package mypac;

public class InterfaceDemo {
	public static void main(String[] args) {
		MyInterface my=new MyInterfaceImpl();
		my.sayHello();
		System.out.println(my.sayHello2("EY"));
		System.out.println(MyInterface.pi);
	}
}
interface MyInterfaceParent{
	public void haha();
}
interface MyInterface extends MyInterfaceParent{
	static final float pi=3.14f;
	public void sayHello();
	public String sayHello2(String name);
}
class MyInterfaceImpl implements MyInterface{
	@Override
	public void sayHello() {
		System.out.println("say hello...");
	}
	@Override
	public String sayHello2(String name) {
		// TODO Auto-generated method stub
		return "Welcome to interfacess....:"+name;
	}
	@Override
	public void haha() {
		// TODO Auto-generated method stub
		
	}
}