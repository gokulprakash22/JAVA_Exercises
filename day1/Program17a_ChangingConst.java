package day1;

public class Program17a_ChangingConst {
	public static void main(String[] args) {
		final int val=10;
		val=20;
		/*
		 *This will cause compile time error.
		 *Error: The final local variable val cannot be assigned. It must be blank and not using a compound assignment
		 */
	}
}