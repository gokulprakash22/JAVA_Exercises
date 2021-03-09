package day2LabBook;

public class Ex04 {
	static int stack[]=new int[10];
	static int c=-1;
	public static void push(int element)
	{
		c++;
		stack[c]=element;
	}
	public static void pop()
	{
		c--;
	}
	public static void main(String[] args) {
		int num=5;
		push(1);
		push(2);
		push(3);
		push(4);
		push(5);
		System.out.println("The stack after pusing elements ");
		for(int i=0;i<5;i++) {
			System.out.print(stack[i]);
		}
		pop();
		pop();
		System.out.println();
		System.out.println("The stack after poping 2 elements ");
		for(int i=0;i<=c;i++) {
			System.out.print(stack[i]);
		}
	}
	
}