package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Stream;

class Employee implements Comparable<Employee> {
	String name;
	public Employee(String name) {
		this.name = name;
		
	}
	@Override
	public String toString() {
		return "Employee name=" + name;
	}
	
	@Override
	public int compareTo(Employee e) {
		return this.name.compareTo(e.name);
	}
}

public class CollectionsDemo1ArrayList {

	public static void main(String[] args) {
		List<Employee> list = new ArrayList<>(10); //advisable to give an initial capacity
		list.add(new Employee("ramu"));
		list.add(new Employee("somu"));
		list.add(new Employee("tina"));
		list.add(new Employee("uma"));
		System.out.println(list);
		
		List<Employee> list2 = Arrays.asList(list.get(0), list.get(1), list.get(2), list.get(3));
		System.out.println(list2);
		
		int size = list.size();
		for(int i=0; i<size; i++) {
			System.out.println(list.get(i));
		}
		
		for(Employee e: list) {
			System.out.println(e);
		}
		
		Iterator<Employee> iter=list.iterator();
		
		while(iter.hasNext()) {
			System.out.println("First...:"+iter.next());
		}
		
		iter = list.iterator();
		while(iter.hasNext()) {
			System.out.println("Second...:"+iter.next());
		}
		
		ListIterator<Employee> liter = list.listIterator();
		
		System.out.println("Next elements:");
		
		while(liter.hasNext()) {
			System.out.println(liter.next());
		}
		
		System.out.println("Previous elements:");
//		prints in reverse
		while(liter.hasPrevious()) {
			System.out.println("Previous....:"+liter.previous());
		}
		
		while(liter.hasNext()) {
			System.out.println("Next..next..:"+liter.next());
		}
		while(liter.hasPrevious()) {
			System.out.println("Previous..previous.:"+liter.previous());
		}
		
		System.out.println("Descending order sort");
		Collections.sort(list, (e1, e2) -> {
			return e2.compareTo(e1);
		});
		System.out.println(list);
		
		System.out.println("Ascending order sort");
		Collections.sort(list);
		System.out.println(list);

		Stream<Employee> s=list.stream();
		s.filter((emp)->emp.name.equals("ramu")).forEach(System.out::println);
	}

}
