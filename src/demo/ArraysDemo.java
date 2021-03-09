package demo;


public class ArraysDemo {
	public static void main(String[] args) {
		int arr[] = new int[5];
		int []arr2 = {2,5,7,9}; 
		//by default arrays are initialized...
		//by default arrays are pass by reference
		//you can use both for loops(old and new)
		//Array is an object so it has properties
		//normally local variables are not initialized but arrays are initialized..
		//arrays are strongly typed
		//arrays once declared u cannot change the size.
		//array index always starts with zero
		//in case if you access a wrong index u will get a abnormal condition-ArrayIndexOutOfBoundsException
		//Strongly typed
		//Array picks up memory in a continuous location
		int arr3[][] = new int[4][5];
		int arr4[][] = {
				{4,6,7,7},
				{4,6,7,8}
				}; 
		//uneven multi dimensional array
		int arr5[][] = new int[3][];
		arr5[0] = new int[2];
		arr5[1] = new int[4];
		arr5[2] = new int[3];
		int arr6[][] = {
				{3,4},
				{5,6,7}
				};
		//reading an array/processing an array
		String s[] = {"hello","world"};
		for(String ss:s) {
			System.out.println(ss);
			}
		int size = s.length;
		for(int i=0;i<size;i++) {
			System.out.println(s[i]);
			}
		//complex type object array
		ArrayCommand command[][] = new ArrayCommand[2][3];
		command[0][0] = new ArrayCommand("first row first column");
		command[0][1] = new ArrayCommand("first row second column");
		command[0][2] = new ArrayCommand("first row third column");
		command[1][0] = new ArrayCommand("second row first column");
		command[1][1] = new ArrayCommand("second row second column");
		command[1][2] = new ArrayCommand("second row third column");
		//to process a two dimensional array, we need nested for loops
		for(ArrayCommand c[]:command) { 
			for(ArrayCommand com:c) {
				System.out.print(com+"\t");
				}
			System.out.println("\n");
			}
		for(int i=0;i<command.length;i++) {
			for(int j=0;j<command[i].length;j++) {
				System.out.print(command[i][j]+"\t");
				}
			System.out.println("\n");
			}
		char c[] = {'a','b','c'};
		System.out.println("Initial value...:"+c[0]);
		//char ccopy[]=c;
		char ccopy[]=new char[3];
		System.arraycopy(c, 0, ccopy, 0, c.length);
		acceptArray(ccopy);
		System.out.println("After passing array..:"+c[0]);
		}
	public static void acceptArray(char cc[]) {
		cc[0]='z';
		}
	}

class ArrayCommand{
	String msg;
	public ArrayCommand(String msg) {
		this.msg=msg;
		}
	@Override
	public String toString() {
		return this.msg;
		}
	}
