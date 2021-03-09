package day04LabBook;

public class Program03{
	public void met(){
		met2();
		System.out.println("Caller");
	}
	public void met2() {
		try {
			throw new ArithmeticException();
			System.exit(0); // This will not be reached
		}
		catch(Exception e) {
			System.out.println("Catch of met2");
		}
		finally {
			System.out.println("Finally of met2");
		}
	}
	public static void main(String[] args) {
		Program03 obj = new Program03();
		obj.met();
	}
}