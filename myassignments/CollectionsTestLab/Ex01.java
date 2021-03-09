package collectionsLab;

import java.util.ArrayList;
import java.util.List;

public class Ex01 {
	public static void main(String[] args) { 
		int i;
		List<List<Integer>> listoflist = new ArrayList<List<Integer>>(); 
		for (i = 1; i <= 10; i++) { 
			List<Integer> row = new ArrayList<Integer>(); 
			for (int j = 1; j <= 10; j++) 
				row.add(i * j); 
			listoflist.add(row); 
		} 
		i=1;
		for (List<Integer> row : listoflist) {
			System.out.print("Table "+i++);
			System.out.println(" "+row); 
		}
	} 
}