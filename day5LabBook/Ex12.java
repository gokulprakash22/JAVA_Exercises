package day5LabBook;

public class Ex12 {
	public static void main(String[] args) {
		int i=1;
		switch (i) {
			case 0:
				System.out.println("zero");
				break;
			case 1:
				System.out.println("one");
			case 2:
				System.out.println("two");
			default:
				System.out.println("default");
		} 
	}
}
//Output: one two default
//Reason: break keyword is not used