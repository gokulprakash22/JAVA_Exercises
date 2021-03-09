package day1;
import java.lang.Math;
import java.util.Scanner;

public class Program15b_CheckingNumber {
	public static boolean isarmstrong(int n) {
		int ans=0,num,temp=n,len=0;
		while(n>0) {
			n=n/10;
			len++;
		}
		n = temp;
		while(n>0) {
			num=n%10;
			ans+=(Math.pow(num, len));
			n=n/10;
		}
		if(temp==ans) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean isperfectnumber(int n) {
		int i,sum=0;
		for(i=1;i<=n/2;i++) {
			if(n%i==0) {
				sum+=i;
			}		
		}
		if(sum==n) {
			return true;
		}
		else {
			return false;
		}	
	}
	public static boolean ispalindrome(int n) {
		int ans=0,num,temp=n;
		while(n>0) {
			num=n%10;
			ans=(ans*10)+num;
			n=n/10;
		}
		if(temp==ans) {
			return true;
		}	
		else {
			return false;
		}	
	}
	public static void main(String[] args) {
		int num, flag=0;
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter the number");
		num=scan.nextInt();
		if(isarmstrong(num))
		{
			System.out.println(num+" is an armstrong number");
			flag = 1;
		}
		if(isperfectnumber(num))
		{
			System.out.println(num+" is a perfect number");
			flag = 1;
		}
		if(ispalindrome(num))
		{
			System.out.println(num+" is a palindrome number");
			flag = 1;
		}
		if(flag==0)
		{
			System.out.println(num+ "is not a armstrong number or a perfect number or a palindrome number");
		}
	}
}
