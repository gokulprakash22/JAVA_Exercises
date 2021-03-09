package day1;
import java.util.Scanner;

public class Program08_FindLocation {
	static int findLocation(int key, int arr[]){
		int len, pos=-1, i;
		len = arr.length;
		for(i=0;i<len;i++) {
			if(arr[i]==key) {
				pos = i;
				break;
			}
		}
		return pos;
	}
	public static void main(String[] args) {
		int n, key, i, pos;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of values");
		n = scan.nextInt();
		int arr[] = new int[n];
		System.out.println("Enter "+n+" values");
		for(i=0;i<n;i++) {
			arr[i] = scan.nextInt();
		}
		System.out.println("Enter the key to find its location");
		key = scan.nextInt();
		pos = findLocation(key, arr);
		if(pos==-1) {
			System.out.println("The key is not exist in array");
		}
		else {
			System.out.println("The index of key is: "+pos);
		}
	}
}
