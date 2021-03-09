package mypac;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Scanner;

public class ExceptionHandlingDemo2 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Item for child 1: ");
		String item1 = sc.next();
		Dog dog = Child.getItem(item1);
		Child child1 = new Child();
		child1.playWithDog(dog);
		
		System.out.println("Item for child 2: ");
		String item2 = sc.next();
		dog = Child.getItem(item2);
		Child child2 = child1.getChildClone();
		child2.playWithDog(dog);
	}
}

abstract class DogExceptions extends Exception {	
	Object object = Proxy.newProxyInstance(Handler.class.getClassLoader(), new Class[] {Handler.class}, new MyInvocationHandler(new Handler1()));
	public abstract void visit();
	String msg;
	
	@Override
	public String toString() {
		return "DogBarkException rised: "+msg;
	}
}

class DogBarkException extends DogExceptions {
	private static DogBarkException obj;
	
	public DogBarkException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public void visit() {
		((Handler)object).handle(this);	
	}
	
	public static DogBarkException getDogBarkException() {
		if(obj == null)
			obj =  new DogBarkException("You throw...I bark...");		
		return obj;
	}
}

class DogBiteException extends DogExceptions {
	private static DogBiteException obj;
	
	public DogBiteException(String msg) {
		this.msg = msg;
	}

	@Override
	public void visit() {
		((Handler)object).handle(this);		
	}
	
	public static DogBiteException getDogBiteException() {
		if(obj == null)
			obj = new DogBiteException("You beat...I bite...");
		return obj;
	}
}

class DogHappyException extends DogExceptions {
	private static DogHappyException obj;

	public DogHappyException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public void visit() {
		((Handler)object).handle(this);
	}
	
	public static DogHappyException getDogHappyException() {
		if(obj == null)
			obj = new DogHappyException("You feed...I eat...");
		return obj;
	}
}

class MyInvocationHandler implements InvocationHandler {
	Object obj;
	
	public MyInvocationHandler(Object obj) {
		this.obj = obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		obj = method.invoke(obj, args);
		return obj;
	}
} 


interface Handler {
	public void handle(DogBiteException de);
	public void handle(DogBarkException dbe);
	public void handle(DogHappyException dhe);
}

class Handler1 implements Handler {
	public void handle(DogBarkException dbe) {
		System.out.println("Barking dogs seldom bite...");
	}
	
	public void handle(DogBiteException de) {
		System.out.println("Emergency...dog has bitten...Ambulance called...");
	}
	
	public void handle(DogHappyException dhe) {
		System.out.println("Dog had its food... Play with the dog...");
	}
}

abstract class Item {
	public abstract void execute() throws DogExceptions;
}

class Stone extends Item {
	public Stone() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void execute() throws DogExceptions {
		throw DogBarkException.getDogBarkException();		
	}
}

class Stick extends Item {
	public Stick() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void execute() throws DogExceptions {
		throw DogBiteException.getDogBiteException();		
	}
}

class Bone extends Item {	
	public Bone() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void execute() throws DogExceptions {
		throw DogHappyException.getDogHappyException();	
	}
}

class Child implements Cloneable {
	public static Dog getItem(String itemName) throws DogExceptions, Exception {
		Item item = (Item)Class.forName(itemName).getConstructor().newInstance();
		Dog dog = new Dog();
		dog.item = item;
		return dog;
	}
	
	public void playWithDog(Dog dog) throws Exception {
		try {
			dog.play(dog.item);
		} catch(DogExceptions de) {
			de.visit();
		}
	}
	
	public Child getChildClone() throws Exception {
		return (Child)super.clone();
	}
}

class Dog implements Cloneable {
	Item item;
	Dog dog;
	
	public void play(Item item) throws DogExceptions {
		item.execute();
	}
	
	Dog getDogClone() throws Exception {
		return (Dog)super.clone();
	}	
}