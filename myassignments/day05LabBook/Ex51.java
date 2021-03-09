package day5LabBook;

public class Ex51{
	private int i;
	public static void main(String argv[]){
		Ex51 s = new Ex51();
		s.amethod();
		}//End of main
	public static void amethod(){
		System.out.println(i); //Compile time time. Cannot make static reference to a non static field.
		}//end of amethod
	}//End of class
