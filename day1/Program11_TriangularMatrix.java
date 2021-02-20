package day1;
import java.util.Scanner;

public class Program11_TriangularMatrix {
	public static void main(String[] args) {
		int rows, cols;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter rows and columns");
		rows = scan.nextInt();
		cols = scan.nextInt();
		int arr[][] = new int[rows][cols];
		int i,j,toprint=1;
		for(i=0;i<rows;i++) {
			for(j=0;j<=i;j++) {
				arr[i][j] = toprint++;
			}
		}
		for(i=0;i<rows;i++) {
			for(j=0;j<=i;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}
