package day2LabBook;

public class Ex07 {
	static int a;
	static{
		System.out.println("Static block is executed");
		a=10;
	}
	public static void main(String[] args) {
		System.out.println("Value of a is "+a);
	}
}