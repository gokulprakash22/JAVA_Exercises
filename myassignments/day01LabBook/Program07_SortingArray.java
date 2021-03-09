package day1;
import java.util.Scanner;

public class Program07_SortingArray {
	static void AscendingSort(int arr[]) {
		int i,j,temp;
		for(i=0;i<arr.length-1;i++) {
			for(j=i+1;j<arr.length;j++) {
				if(arr[i]>arr[j]) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
	static void DescendingSort(int arr[]) {
		int i,j,temp;
		for(i=0;i<arr.length-1;i++) {
			for(j=i+1;j<arr.length;j++) {
				if(arr[i]<arr[j]) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
	public static void main(String[] args) {
		int n, i;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of values");
		n = scan.nextInt();
		int arr[]=new int[n];
		System.out.println("Enter "+n+" values");
		for(i=0;i<n;i++) {
			arr[i] = scan.nextInt();
		}
		AscendingSort(arr);
		System.out.println("Array in ascending order");
		for(int num:arr) {
			System.out.print(num+" ");
		}
		DescendingSort(arr);
		System.out.println("\nArray in descending order");
		for(int num:arr) {
			System.out.print(num+" ");
		}
	}
}
