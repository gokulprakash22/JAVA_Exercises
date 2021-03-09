package day5LabBook;

public class Ex02 {
	 public static void main(String arguments[]) {
		 amethod(arguments);
		 }
	 public void amethod(String[] arguments) { //Method should be static to access this method without an object
		 System.out.println(arguments);
		 System.out.println(arguments[1]);
		 }  
}
