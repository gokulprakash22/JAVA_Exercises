package day1;

public class Program23_ProtectedData {
	public static void main(String[] args) {
		ClassB classbobj=new ClassB();
		classbobj.met();
	}
}
class ClassA{
	protected int var=10;
}
class ClassB{
	public void met()
	{
		ClassA obj=new ClassA();
		System.out.println("Before manipulation: "+obj.var);
		obj.var = 20;
		System.out.println("After manipulation: "+obj.var);
	}
}