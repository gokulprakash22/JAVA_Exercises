package day5LabBook;

class Background implements Runnable{
	 int i=0;
	 public int run(){ //Compile time error. run method should be void
	 while(true){
	 i++;
	 System.out.println("i="+i);
	 } //End while
	 return 1;
	 }//End run
	}//End class 
