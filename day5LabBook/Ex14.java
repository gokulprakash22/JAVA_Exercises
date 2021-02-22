package day5LabBook;

public class Ex14 {
	public static void main(String[] args) {
		int i=0;
		if(i) { //Error: Can't convert int to boolean
			System.out.println("Hello");
			}
		
		boolean b=true;
		boolean b2=true;
		if(b==b2) {
			System.out.println("So true");
			} // No error
		
		int j1=1;
		int j2=2;
		if(j1==1 || j2==2)
			System.out.println("OK"); //No error
		
		int k1=1;  int k2=2;
		if(k1==1 &| k2==2) //Syntax error
			System.out.println("OK");
	}
}
