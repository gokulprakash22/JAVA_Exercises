package collectionsLab;

import java.util.PriorityQueue;

public class Ex10 {
public static void main(String[] args) {
	 PriorityQueue<String> priorityquueue = new PriorityQueue<String>(); 
	 priorityquueue.add("10"); 
	 priorityquueue.add("20"); 
	 System.out.print(priorityquueue.peek() + " "); //2
	 priorityquueue.offer("30"); 
	 priorityquueue.add("40"); 
	 priorityquueue.remove("1"); 
	 System.out.print(priorityquueue.poll() + " "); //2
	 if(priorityquueue.remove("2")) System.out.print(priorityquueue.poll() + " "); //3 
	 System.out.println(priorityquueue.poll() + " " + priorityquueue.peek()); //4
}
}
