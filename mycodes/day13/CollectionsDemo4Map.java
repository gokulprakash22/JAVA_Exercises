package collections;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CollectionsDemo4Map {
	public static void main(String[] args) {
		Map<MyKey, String> map = new TreeMap<>((o1, o2) -> {return o2.compareTo(o1);});
		MyKey key1 = new MyKey("a1");
		MyKey key2 = new MyKey("a2");
		MyKey key3 = new MyKey("a3");
		MyKey key4 = new MyKey("a4");
		
		map.put(key1, "ramu");
		map.put(key2, "somu");
		map.put(key3, "raju");
		map.put(key4, "rohan");
		
		System.out.println(map);
		
		String result = map.get(key4);
		System.out.println(result);
		
		Set<Map.Entry<MyKey, String>> set = map.entrySet();
		 Iterator<Map.Entry<MyKey, String>> iter = set.iterator();
		 
		 while(iter.hasNext()) {
			 Map.Entry<MyKey, String> me = iter.next();
			 System.out.println(me.getKey()+" : "+me.getValue());
		 }
	}
}

class MyKey implements Comparable<MyKey> {
	String key;
	
	public MyKey(String key) {
		this.key = key;
	}
	
	@Override
	public int compareTo(MyKey o) {
		return this.key.compareTo(o.key);
	}

	@Override
	public String toString() {
		return this.key;	
	}
	
	
}