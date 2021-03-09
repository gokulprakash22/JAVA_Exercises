package collections;

import java.util.Map;
import java.util.WeakHashMap;

public class CollectionsDemo5WeakHashMap {
	public static void main(String[] args) {
		Map<MyKey, String> map = new WeakHashMap<MyKey, String>();
		MyKey key1 = new MyKey("a1");
		MyKey key2 = new MyKey("a2");
		MyKey key3 = new MyKey("a3");
		MyKey key4 = new MyKey("a4");
		
		map.put(key1, "ramu");
		map.put(key2, "somu");
		map.put(key3, "raju");
		map.put(key4, "rohan");
;
		key1 = null;
		System.out.println("Before GC");
		System.out.println(map);
		System.gc();
		System.out.println("After GC");
		System.out.println(map);
	 }
	
}
