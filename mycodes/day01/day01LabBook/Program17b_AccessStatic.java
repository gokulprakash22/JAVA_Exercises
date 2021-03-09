package day1;

public class Program17b_AccessStatic {
	private static int aStaticVariable = 1;
	private void aNonStaticMethod() { 
		System.out.println(aStaticVariable); 
		}
	public static void main(String[] args) {
		Program17b_AccessStatic obj=new Program17b_AccessStatic();
		obj.aNonStaticMethod();
	}
}