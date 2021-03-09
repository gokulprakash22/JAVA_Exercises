package day2LabBook;

public class Ex05 {
	static int c;
	public Ex05() {
		c++;
	}
	public static void main(String[] args) {
		Ex05 obj1=new Ex05();
		Ex05 obj2=new Ex05();
		Ex05 obj3=new Ex05();
		Ex05 obj4=new Ex05();
		System.out.println("No of Objects for the class is "+c);
	}
}
