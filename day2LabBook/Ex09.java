package day2LabBook;

public class Ex09 {
	public static void main(String[] args) {
		B obj=new B();
		obj.access();
	}
}
class A
{
	public int a=10;
	protected int b=20;
	private int c=30;
}
class B extends A
{
	public void access()
	{
		// System.out.println("The private variable of class A is "+c); cannot access private variable of the base class
		System.out.println("The protected variable of class A is "+b);
		System.out.println("The public variable of class A is "+a);
	}
}