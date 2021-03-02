package day4Lab;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program07 {
	public static void main(String[] args) {
		String num="",div="";
		int res=0;
		Scanner scan=new Scanner(System.in);
		num=scan.next();
		div=scan.next();
		while(true)
		{
			try {
			res=Integer.parseInt(num)/Integer.parseInt(div);
			System.out.println("Result is "+res);
			}
			catch(ArithmeticException e) {System.out.println("Divide By Zero");}
			catch(NumberFormatException e) {System.out.println("Poor Input");}
			num=scan.next();
			if(num.equals("q") || num.equals("Q")) break;
			div=scan.next();
		}
	}
}