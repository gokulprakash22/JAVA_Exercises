package day5LabBook;

abstract class MineBase {
	abstract void amethod();
	static int i;
	}

public class Ex11 extends MineBase {
	public static void main(String argv[]) {
		int[] ar = new int[5];
		for(i=0;i < ar.length;i++)
			System.out.println(ar[i]);
		}
	}
//Error: Ex11 class must implement the inherited abstract method amethod.
//Solution: Make Ex11 as abstarct class or implement amethod method.