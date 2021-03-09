package day5LabBook;

public class Ex42 {
	public static void main(String argv[]) {
		Ex42 l = new Ex42();
		l.amethod();
		}
	public void amethod() {
		int ia[] = new int[4];
		for(int i=0; i< ia.length;i++) {
			ia[i]=i;
			System.out.println(ia[i]);
			}
		}
	} 
//Output: 0 1 2 3