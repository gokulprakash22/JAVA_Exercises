package day1;

public class Program24_QuadraticEquation {

	//equation of the form ax^2+bx+c
	private int a;
	private int b;
	private int c;
	
	public Program24_QuadraticEquation() {
		this.a=1;
		this.b=1;
		this.c=1;
	}
	
	public Program24_QuadraticEquation(int a,int b,int c) {
		this.a=a;
		this.b=b;
		this.c=c;
	}
	
	public void modifyfunction(int a,int b,int c) {
		this.a=a;
		this.b=b;
		this.c=c;
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public int getC() {
		return c;
	}

	
	public long compute(int x) {
		return (this.a*(x*x))+(this.b*x)+this.c;
		
	}
	public static void main(String[] args) {
		Program24_QuadraticEquation obj1=new Program24_QuadraticEquation(); //using constructor without args
		System.out.println("Result with Default Values "+obj1.compute(2));
		obj1.getA();
		obj1.getB();
		obj1.getC();
		obj1.modifyfunction(2, 1, 4);
		System.out.println("Result with Modified Values "+obj1.compute(2));
		Program24_QuadraticEquation obj2=new Program24_QuadraticEquation(2,3,4); //using constructor with args
		System.out.println("Result with Constructor Values "+obj2.compute(2));

	}
}