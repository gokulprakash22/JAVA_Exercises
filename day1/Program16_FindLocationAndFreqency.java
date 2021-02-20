package day1;

import java.util.Scanner;

public class Program16_FindLocationAndFreqency {
	static void findLocationAndFrequency(int arr[],int value)
	{
		int count=0,i;
		for(i=0;i<arr.length;i++)
		{
			if(arr[i]==value)
			{
				System.out.println("Element "+value+" is Found at Index "+i);
				count++;
			}
		}
		System.out.println("The element occured "+count+" times");
	}
	public static void main(String[] args) {
		int n, i, key;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of values");
		n = scan.nextInt();
		int arr[]=new int[n];
		System.out.println("Enter "+n+" values");
		for(i=0;i<n;i++) {
			arr[i] = scan.nextInt();
		}
		System.out.println("Enter the key");
		key = scan.nextInt();
		findLocationAndFrequency(arr,key);
		
	}
}
