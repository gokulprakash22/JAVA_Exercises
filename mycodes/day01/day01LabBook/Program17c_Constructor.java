package day1;

public class Program17c_Constructor {
	public static void main(String[] args) {
		 HelloWorldClass hw=new HelloWorldClass();
		 /*
		  *This will cause compile time error.
		  *Error: The constructor HelloWorldClass() is undefined
		  */
	}
}
class HelloWorldClass
{
	HelloWorldClass(int a)
	{
		System.out.println("Hello World With Args Constructor");
	}
}