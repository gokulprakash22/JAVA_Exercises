package collections;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class CollectionsDemo2Vector {
	public static void main(String[] args) {
		Vector<String> v = new Vector<>();
		v.add("hello");
		v.add("hi");
		v.add("Bye");
		
		System.out.println(v);
		
		Enumeration<String> e = v.elements();
//		Iterator<String> iter = v.iterator(); 
//		iterator is fail fast, cannot modify the structure after it is declared
		
		v.add("world");
		while(e.hasMoreElements()) {
			System.out.println("Enumerator..."+e.nextElement());
		}
		
		
		Iterator<String> iter = v.iterator(); 
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}