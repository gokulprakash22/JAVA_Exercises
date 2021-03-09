package mypac;

public class VariablesAndConstructorsDemo {
	public static void main(String[] args) {
		TrainingRoom room1 = new TrainingRoom();
		room1.teach();
		room1.teach();
		TrainingRoom room2 = new TrainingRoom();
		room1.teach();
		room1.teach();
	}
}

class Chalk{
	public Chalk() { //Constructor - Special method whose name matches with class name with return or void
		System.out.println("chalk obj created");
	}
}

class Canteen{
	public Canteen() {
		System.out.println("canteen obj created");
	}
}

class Projector{
	public Projector() {
		System.out.println("projector obj created");
	}
}

class TrainingRoom{
	public TrainingRoom() {
		System.out.println("training room obj created");
	}
	public void teach() {
		Chalk chalk = new Chalk(); //A non static variable inside a method - local variable
		//local variable cannot be static
		System.out.println("teach method completed");
	}
	Projector projector = new Projector(); //A non static variable inside a class outside a method - instance variable
	static Canteen canteen = new Canteen(); //A static variable inside a class outside a method - class variable
}

//A Constructor is a special method which gets called during the object creation
//What ever needed for the mere survival of the object, those properties can be initialized in the constructor.
