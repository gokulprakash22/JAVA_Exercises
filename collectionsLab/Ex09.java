package collectionsLab;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Ex09 {
	public static void main(String[] args) {
		Object o = new Object(); 
//		Set s = new HashSet(); //compiles executes without exception 
		TreeSet s = new TreeSet(); //compiles executes without exception 
//		LinkedHashSet s = new LinkedHashSet(); //compiles executes without exception 
		
		s.add("o");  // adding string
		s.add(o); //adding object
	}
}