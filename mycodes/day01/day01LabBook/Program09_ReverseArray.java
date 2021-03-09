package day1;
import java.util.Scanner;

public class Program09_ReverseArray {
	static void reverse(int arr[])
	{
		int i,temp,j=arr.length-1;
		for(i=0;i<arr.length/2;i++)
		{
			temp=arr[i];
			arr[i]=arr[j];
			arr[j]=temp;
			j--;
		}
	}
	public static void main(String[] args) {
		int n, i;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of values");
		n = scan.nextInt();
		int arr[] = new int[n];
		System.out.println("Enter "+n+" values");
		for(i=0;i<n;i++) {
			arr[i] = scan.nextInt();
		}
		reverse(arr);
		System.out.println("Reversed array");
		for(int num:arr) {
			System.out.print(num+" ");
		}
		
	}
}
