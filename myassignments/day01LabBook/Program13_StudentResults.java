package day1;
import java.util.Scanner;

public class Program13_StudentResults {
	public static void main(String[] args) {
		int students, subjects;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter number of students");
		students = scan.nextInt();
		System.out.println("Enter number of subjects");
		subjects = scan.nextInt();
		int arr[][] = new int[students][subjects];
		int i,j;
		for(i=0;i<students;i++) {
			System.out.println("Enter student "+(i+1)+" marks");
			for(j=0;j<subjects;j++) {
				arr[i][j] = scan.nextInt();
			}
		}
		int total, sum;
		total = subjects*100;
		for(i=0;i<students;i++) {
			sum = 0;
			for(j=0;j<subjects;j++) {
				sum+=arr[i][j];
			}
			System.out.println("Student "+(i+1)+" secured "+sum+" out of "+total);
		}
	}
}
