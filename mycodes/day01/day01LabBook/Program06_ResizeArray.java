package day1;
import java.util.Scanner;

import java.util.Arrays;

public class Program06_ResizeArray {
	public static void main(String[] args) {
		int n, i, new_size;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of values");
		n = scan.nextInt();
		int arr[] = new int[n];
		System.out.println("Enter "+n+" values");
		for(i=0;i<n;i++) {
			arr[i] = scan.nextInt();
		}
		System.out.println("Enter the new size");
		new_size = scan.nextInt();
		System.out.println("The length of original array: "+arr.length);
		System.out.print("Original array: ");
		for(int num:arr) {
			System.out.print(num+" ");
		}
		arr=Arrays.copyOf(arr, new_size);
		System.out.println("\nThe length of modified array: "+arr.length);
		System.out.print("Modified array: ");
		for(int num:arr) {
			System.out.print(num+" ");
		}
	}
}