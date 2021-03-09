package day2LabBook;

public class Ex10 {
	public static void main(String[] args) {
		ClassC c=new ClassC();
	}
}

class ClassA{
	public ClassA() {
		System.out.println("Class A");
	}
}
class ClassB extends ClassA{
	public ClassB() {
		System.out.println("Class B");
	}
}
class ClassC{
	ClassB b=new ClassB();
}