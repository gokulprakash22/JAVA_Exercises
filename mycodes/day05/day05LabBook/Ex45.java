package day5LabBook;

public class Ex45{
	public static void main(String argv[]){
		Ex45 r = new Ex45();
		r.amethod(r);
		}
	public void amethod(Ex45 r){
		int i=99;
		multi(r);
		System.out.println(i);
		}
	public void multi(Ex45 r){
		r.i = r.i*2; //Compile time error. i is a local variable.
		}
	} 

