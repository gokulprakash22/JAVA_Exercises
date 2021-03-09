package day5LabBook;

import java.io.*;
public class Ex15 {
	public static void main(String argv[]){
		Ex15 m=new Ex15();
		System.out.println(m.amethod());
	}
public int amethod() {
	try {
		FileInputStream dis=new FileInputStream("Hello.txt");
	}
	catch (FileNotFoundException fne) {
		System.out.println("No such file found");
		return -1;
	}
	catch(IOException ioe) {
	} 
	finally{
		System.out.println("Doing finally");
	}
	return 0;
 	}
	} 
 //Output: No such file found Doing finally -1