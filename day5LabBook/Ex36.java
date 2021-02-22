package day5LabBook;

public class Ex36{
	 public static void main(String argv[]){
		 Ex36 e=new Ex36();
	 }
	 Ex36(){
		 String s="Java";
		 String s2="java";
		 if(s.equalsIgnoreCase(s2)) { //This method will ignore case sensitive
			 System.out.println("Equal");
	 		}
	 	 else {
	 		System.out.println("Not equal");
	 	 	}
	 	}
	}
//Output: Equal