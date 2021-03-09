package yourpack;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IntrospectionPoliticianDemo {
	public static void main(String[] args) throws Exception {
		PoliceStation police = (PoliceStation)Class.forName("day9.PoliceStation").getConstructor(new Class[] {String.class}).newInstance("helloo");
		Politician politician = (Politician)Class.forName("day9.Politician").getConstructor().newInstance();
		police.arrest(politician);
	}
}

class PoliceStation {
	public PoliceStation(String s) {
		System.out.println("Overloaded string constructor of PoliceStation called"+s);
	}
	
	public void arrest(Object accused) throws Exception {
		Class c = accused.getClass();
		Field fields[] = c.getFields();
		for(Field field: fields) {
			System.out.println(field.getName()); //name
		}
		
		Field f = c.getField("name");
		System.out.println(f);  //public java.lang.String day9.Politician.name
		System.out.println(f.get(accused)); //good man
		
		Method method = c.getMethod("service", new Class[] {int.class});
		String value = (String)method.invoke(accused, new Object[] {Integer.valueOf(100)});
		System.out.println("Return of service method: "+value);
		
		f = c.getDeclaredField("secretName");
		f.setAccessible(true);
		System.out.println(f.get(accused));
		
		method = c.getDeclaredMethod("secretService", new Class[] {int.class});
		method.setAccessible(true);
		value = (String)method.invoke(accused, new Object[] {Integer.valueOf(100)});
		System.out.println("Return of secretService method: "+value);
	}
}

class Politician {
	public Politician() {
		// TODO Auto-generated constructor stub
	}
	public String name = "good man";
	private String secretName = "bad name";
	
	public String service(int money) {
		return "Since you gave me money, I am your friend";
	}
	
	private String secretService(int money) {
		return "Since I am a bad man, I will cheat you and take your money";
	}
}