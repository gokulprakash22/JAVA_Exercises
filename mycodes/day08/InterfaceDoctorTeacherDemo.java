package day10;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Scanner;

public class InterfaceDoctorTeacherDemo {
	public static void main(String[] args) {
		/*
		 * Interface is a special class whose activity is promised by the class which implements it, and when an behavioral object is subjected
		 * to the implementation, the behavioral object becomes a component.
		 *
		 * Doctor is a special class whose activity is implemented in medical college, when shoiab is subjected to medical college, shoiab becomes a
		 * part of(Component) medical fraternity as doctor, and people REALIZE the objective of getting cured by going to shoiab, who in turn is
		 * now playing the role of doctor.
		 *
		 */

		Object gokul = new Human();
		System.out.println("gokul born");
		Scanner scan = new Scanner(System.in);
		scan.next();
		StanleyAlopathyMC stanley = new StanleyAlopathyMC();
		gokul = Proxy.newProxyInstance(Human.class.getClassLoader(), new Class[] {Doctor.class}, new MyInvocationHandler(stanley));
		Doctor doctorgokul = (Doctor)gokul;
		doctorgokul.doCure();
		TeachAcademy tacademy = new TeachAcademy();
		gokul = Proxy.newProxyInstance(Human.class.getClassLoader(), new Class[] {Teacher.class}, new MyInvocationHandler(tacademy));
		Teacher teachergokul = (Teacher)gokul;
		teachergokul.doTeach();
		doctorgokul.doCure();
		teachergokul.doTeach();
		doctorgokul.doCure();
	}
}
class MyInvocationHandler implements InvocationHandler{
	Object obj;
	public MyInvocationHandler(Object obj) {
		this.obj = obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object o = method.invoke(obj, args);
		return o;
	}
}
class Human {
}

interface Doctor {
	public void doCure();
}

class StanleyAlopathyMC implements Doctor{
	@Override
	public void doCure() {
		System.out.println("Curing");
	}
}

interface Teacher{
	public void doTeach();
}

class TeachAcademy implements Teacher{
	@Override
	public void doTeach() {
		System.out.println("Teaching");
	}
}