package collections;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class CollectionsDemo3Set {
	public static void main(String[] args) {
		Set<String> myset = new TreeSet<String>((o1, o2)-> {return o2.compareTo(o1);}); 
//		stores in ascending order by default
		myset.add("hello");
		myset.add("hi");
		myset.add("bye");
		
		System.out.println(myset);
		
		for(String str: myset) {
			System.out.println(str);
		}
		
		Iterator<String> iter = myset.iterator();
		
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}