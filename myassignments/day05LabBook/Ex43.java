package day5LabBook;

private class Base3 { //Compile time error. Class can't be private
	Base3() {
		int i = 100;
		System.out.println(i);
		}
	}
public class Ex43 extends Base3 {
	static int i = 200;
	public static void main(String argv[]) {
		Ex43 p = new Ex43();
		System.out.println(i);
		}
	} 
