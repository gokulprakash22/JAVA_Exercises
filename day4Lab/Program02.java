package day4Lab;

public class Program02{
	public void met(){
		met2();
		System.out.println("Caller");
	}
	public void met2() {
		try {
			throw new ArithmeticException();
			return; // This will not be reached
		}
		catch(Exception e) {
			System.out.println("Catch of met2");
		}
		finally {
			System.out.println("Finally of met2");
		}
	}
	public static void main(String[] args) {
		Program02 obj = new Program02();
		obj.met();
	}
}