package day03LabBook;

public class Ex03 {
	public static void main(String[] args) {
		FixedStack fs = new FixedStack(4);
		DynamicStack ds = new DynamicStack(3);
		// Fixed stack
		System.out.println("Adding elements to fixed stack:");
		for(int i = 1; i <= 4; i++) {
			fs.push(i);
		}
		System.out.println("Elements in fixed stack:");
		for(int i = 0; i < 4; i++) {
			System.out.println(fs.pop());
		}
		
		// Dynamic stack
		System.out.println("Adding elements to dynamic stack:");
		for(int i = 1; i <= 6; i++) {
			ds.push(i); // Original capacity is 3 but 6 elements are added
		}
		System.out.println("Elements in dynamic stack:");
		for(int i = 0; i < 6; i++) {
			System.out.println(ds.pop());
		}
	}
}

interface Stack {
	void push(int element);
	int pop();
}

class FixedStack implements Stack {
	private int stack[];
	private int top;
	
	FixedStack(int size) {
		stack = new int[size];
		top = -1;
	}
	
	public void push(int element) {
		if(top == stack.length - 1) {
			System.out.println("Overflow");
		}
		else {
			stack[++top] = element;
		}
	}
	
	public int pop() {
		if(top < 0) {
			System.out.println("Underflow");
			return 0;
		}
		else {
			return stack[top--];
		}
	}
}

class DynamicStack implements Stack {
	private int stack[];
	private int top;
	
	DynamicStack(int size) {
		stack = new int[size];
		top = -1;
	}
	
	public void push(int element) {
		if(top == stack.length - 1) {
			System.out.println("Overflow");
			// Dynamically increase the capacity of the stack
			int newStack[] = new int[stack.length * 2];
			for(int i = 0; i < stack.length; i++) {
				newStack[i] = stack[i];
			}
			stack = newStack;
			stack[++top] = element;
		}
		else {
			stack[++top] = element;
		}
	}
	
	public int pop() {
		if(top < 0) {
			System.out.println("Underflow");
			return 0;
		}
		else {
			return stack[top--];
		}
	}
}