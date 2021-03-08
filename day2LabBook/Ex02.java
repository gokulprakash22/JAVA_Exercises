package day2LabBook;

public class Ex02 {
	public static void main(String[] args) {
		Complex c=new Complex(1,2);
		System.out.println("After Adding Two Complex Numbers");
		System.out.println(c.addComplexNumbers(new Complex(3,4)));
	}
}
class Complex{
	int r,i;
	public Complex(int r,int i) {
		this.r=r;
		this.i=i;
	}
	public String addComplexNumbers(Complex number) {
		return String.valueOf(number.r+this.r)+" +i"+String.valueOf(number.i+this.i);
	}
}
