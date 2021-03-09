package day5LabBook;

class Base4 {
	 Base4(int i) {
		 System.out.println("base constructor");
	 	}
	 Base4() {
	 	}
	} 
public class Ex40 extends Base4{
	 public static void main(String argv[]){
		 Ex40 s= new Ex40();
	 //One
	 }
	 Ex40()
	 {
		 //Two
		 super(10); //On the line After //Two put super(10); 
	 }
	 public void derived()
	 {
	 //Three
	 }
	}