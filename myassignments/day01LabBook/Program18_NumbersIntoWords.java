package day1;
import java.util.Scanner;

public class Program18_NumbersIntoWords {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter the number");
		int num=scan.nextInt();
		int rev=0;
		int c,n;
		c=String.valueOf(num).length();
		String ans="";
		String[] units= {"zero","one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
		String[] tens= {"twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
		while(num>0)
		{
			rev=(rev*10)+(num%10);
			num=num/10;
		}
		n=rev;
		while(n>0)
		{
			num=n%10;
			if(c==3)
			{
				ans=ans+units[num]+" hundred ";
			}
			if(c==2)
			{
				if(num<2)
				{
					int t=(num*10);
					n=n/10;
					t+=n%10;
					ans=ans+units[t];
				}
				else
				ans=ans+tens[num-2]+" ";
			}
			if(c==1)
			{
				ans=ans+units[num];
			}
			c--;
			n=n/10;
		}
		System.out.println(ans);
	}
}