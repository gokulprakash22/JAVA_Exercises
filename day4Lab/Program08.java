package day4Lab;

import java.util.Scanner;

public class Program08 {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int num=5,input = 0,sum=0;
		float avg;
		while(num>0)
		{
			System.out.println(input);
			try {
				input=scan.nextInt();
				sum+=input;
			}
			catch(Exception e) {
				continue;
			}
			num--;
		}
		System.out.println(sum);
	}
}