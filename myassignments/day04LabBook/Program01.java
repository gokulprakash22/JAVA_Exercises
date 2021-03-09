package day04LabBook;

import java.io.IOException;
import java.util.Scanner;

public class Program01 {
	public static void main(String[] args) {
		int i;
		Scanner scan = new Scanner(System.in);
		i = scan.nextInt();
		System.out.println(i);
	}
	public void met() {
		throw new IOException(); //Shows error
	}
	public void met2() {
		try {
			throw new IOException(); //Not showing error
		}
		catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		
	}
}