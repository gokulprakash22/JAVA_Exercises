package day5LabBook;

public class Ex41{
	static int j=20;
	public static void main(String argv[]){
		int i=10;
		Ex41 p = new Ex41();
		p.amethod(i);
		System.out.println(i);
		System.out.println(j);
		}
	public void amethod(int x){
		x=x*2;
		j=j*2;
		}
	} 
//Output: 10 40
