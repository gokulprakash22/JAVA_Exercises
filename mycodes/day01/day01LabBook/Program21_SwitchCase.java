package day1;
import java.util.Scanner;

public class Program21_SwitchCase {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int year;
		String month="";
		System.out.println("Enter the year");
		year=scan.nextInt();
		System.out.println("Enter the month");
		month=scan.next();
		switch(month) {
		case "January":System.out.println("No of days is 31"); break;
		case "February":
			if((year%400==0) || (year%4==0 && year%100!=0))
			{
				System.out.println("No of days is 29");
			}
			else 
			{
				System.out.println("No of days is 28");
			}
			break;
		case "March":System.out.println("No of days is 31"); break;
		case "April":System.out.println("No of days is 30"); break;
		case "May":System.out.println("No of days is 31"); break;
		case "June":System.out.println("No of days is 30"); break;
		case "July":System.out.println("No of days is 31"); break;
		case "August":System.out.println("No of days is 31"); break;
		case "September":System.out.println("No of days is 30"); break;
		case "October":System.out.println("No of days is 31"); break;
		case "November":System.out.println("No of days is 30"); break;
		case "December":System.out.println("No of days is 31"); break;
		default: System.out.println("Given month is incorrect");
		}
	}
}
