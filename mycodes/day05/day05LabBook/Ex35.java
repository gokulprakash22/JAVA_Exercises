package day5LabBook;

public class Ex35{
	 public static void main(String argv[]){
	 Ex35 c=new Ex35();
	 String s=new String("ello");
	 c.amethod(s);
	 }
	public void amethod(String s){
	 char c='H';
	 c+=s; //Compile time error. char and string can't be concatenate.
	 System.out.println(c);
	 }
	} 

