package day5LabBook;

public class Ex28 {
	public static void main(String[] args) {
		String s1=new String("Hello");
		String s2=new String("there");
		String s3=new String();
		s3 = s1 + s2; //No error
		s3 = s1-s2; //Error
		s3 = s1 & s2; //Error
		s3 = s1 && s2 //Error
	}
}
