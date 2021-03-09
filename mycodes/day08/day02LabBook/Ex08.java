package day2LabBook;

class SingletonClass {
	private static SingletonClass obj=new SingletonClass();
	private SingletonClass() {}
	public static SingletonClass retriveInstance() {
		return obj;
	}
	public static void method() {
		System.out.println("Method called");
	}

}
public class Ex08 {
	public static void main(String[] args) {
		SingletonClass object=SingletonClass.retriveInstance();
		object.method();
	}
}