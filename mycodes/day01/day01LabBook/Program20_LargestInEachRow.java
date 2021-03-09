package day1;

import java.util.Scanner;

public class Program20_LargestInEachRow {
	public static void main(String[] args) {
		int rows, cols;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter rows and columns");
		rows = scan.nextInt();
		cols = scan.nextInt();
		int arr[][] = new int[rows][cols];
		int i,j,max;
		for(i=0;i<rows;i++) {
			System.out.println("Enter row "+(i+1)+" vaules");
			for(j=0;j<cols;j++) {
				arr[i][j] = scan.nextInt();
			}
		}
		for(i=0;i<rows;i++) {
			max=0;
			for(j=0;j<cols;j++)
			{
				if(arr[i][j]>max)
				{
					max=arr[i][j];
				}
			}
			System.out.println("Largest in row "+(i+1)+" is "+max);
		}
	}
}
