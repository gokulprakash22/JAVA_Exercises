package day03LabBook;

public class Ex06 {
	public static void main(String[] args) {
		Inherited i = new Inherited();
		i.newMethod();
	}
	protected void method() {
		System.out.println("Protected method from InterfacesExercises package.");
	}
}

class Inherited extends Ex06 {
	public void newMethod() {
		super.method();
	}
}