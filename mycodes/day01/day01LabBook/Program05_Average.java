package day1;
import java.util.Scanner;

public class Program05_Average {
	public double average(int arr[]) {
		int i,sum=0, len=arr.length;
		for(i=0;i<len;i++) {
			sum+=arr[i];
		}
		return (double)sum/len;
	}
	public static void main(String[] args) {
		int i,n;
		Scanner scan=new Scanner(System.in);
		Program05_Average avgobj=new Program05_Average();
		System.out.println("Enter the number of values");
		n=scan.nextInt();
		int arr[]=new int[n];
		System.out.println("Enter "+n+" values");
		for(i=0;i<n;i++) {
			arr[i]=scan.nextInt();
		}
		System.out.println("The average of n values is "+avgobj.average(arr));
	}
}
