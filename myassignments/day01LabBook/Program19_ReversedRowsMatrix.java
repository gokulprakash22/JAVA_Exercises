package day1;
import java.util.Scanner;

public class Program19_ReversedRowsMatrix {
	public static void main(String[] args) {
		int rows, cols;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter rows and columns");
		rows = scan.nextInt();
		cols = scan.nextInt();
		int arr[][] = new int[rows][cols];
		int i,j,temp;
		for(i=0;i<rows;i++) {
			System.out.println("Enter row "+(i+1)+" vaules");
			for(j=0;j<cols;j++) {
				arr[i][j] = scan.nextInt();
			}
		}
		for(i=0;i<rows;i++)
		{
			for(j=0;j<cols/2;j++)
			{
				temp = arr[i][j];
				arr[i][j] = arr[i][cols-j-1];
				arr[i][cols-j-1] = temp; 
			}
		}
		System.out.println("Reversed rows matrx");
		for(i=0;i<rows;i++)
		{
			for(j=0;j<cols;j++)
			{
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}
