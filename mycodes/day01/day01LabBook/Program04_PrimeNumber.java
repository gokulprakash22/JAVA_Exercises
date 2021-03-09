package day1;
import java.util.Scanner;
import java.lang.Math;

public class Program04_PrimeNumber {
	public static void main(String[] args) {
		int num, i, flag=1;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number");
		num = scan.nextInt();
		for(i=2;i<=Math.sqrt(num);i++) {
			if(num%i==0) {
				flag=0;
				break;
			}
		}
		if(flag==1) {
			System.out.println(num+" is a Prime Number");
		}
		else {
			System.out.println(num+" is a Composite number");
		}
	}
}
