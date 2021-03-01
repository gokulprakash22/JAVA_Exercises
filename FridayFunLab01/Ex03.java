package FridayFunLab01;
import java.util.Scanner;

public class Ex03 {
	public static void main(String[] args) {
		String s;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a string");
		s = scan.nextLine();
		StringBuffer buffer=new StringBuffer(s);
		System.out.println(buffer.reverse());
	}
}
