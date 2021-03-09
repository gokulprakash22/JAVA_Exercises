package demo;

public class MethodsDemo {
	//Primitive data types
	int i=1;
	float f=1.1f;
	double d=2.2;
	String s="hello";
	char c='a';
	boolean b=true;
	
	public static void main(String[] args) {
		MethodsDemo obj=new MethodsDemo();
		obj.met1(1);
		obj.met1(1,2,3,4,5,6,7);
		obj.met2("1");
		String s=obj.met3("world");
		System.out.println(s);
	}
	
	public void met1(int... i) {
		//public void aaa() {} - method in method is not permitted
		class A{ //- Local inner class
			public void aaa() {}  //this is permitted
		}
		for(int x:i) {//latest for loop or enhanced for loop or for-each loop
			System.out.println(x);
		}
	}
	
	public int met1(int i) {
		System.out.println("met with ordinary param..:"+i);
		return 1;
	}
	
	public int met2(String s) {
		System.out.println("met with string...:"+s);
		return 1;
	}
	
	public String met3(String s) {
		return "hello.."+s;
	}
	public Hello met4() {
		return new Hello();
	}
}

class Hello{}
