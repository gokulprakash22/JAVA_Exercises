package day4Lab;

import java.util.Scanner;

public class Program09 {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int num;
		while(true)
		{
			try {
				num=scan.nextInt();
				if(num<0 || num>100) throw new MyException();
			}
			catch(Exception e) {}
		}
	}
}
class MyException extends Exception 
{ 
    public MyException() 
    { 
        super(); 
    	System.out.println("Exception Caught");
    } 
} 