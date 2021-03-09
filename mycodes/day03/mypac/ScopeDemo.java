package mypac;

//File should contain only one public class
public class ScopeDemo {
	private int pri;
	int nomod;
	protected int pro;
	public int pub;
	
	public static void main(String[] args) { //Starting point
		System.out.println("Hello World"); //Prints in console
	}
	
	//Same package Same class
	public void met() {
		System.out.println(pri);
		System.out.println(nomod);
		System.out.println(pro);
		System.out.println(pub);	
	}
}

class SubTest extends ScopeDemo{
	//Same package Different subclass
	public void met() {
		//System.out.println(pri);
		System.out.println(nomod);
		System.out.println(pro);
		System.out.println(pub);	
	}
}

class NoSubTest{
	//Same package different non-subclass 
	public void met() {
		ScopeDemo obj = new ScopeDemo();
		//System.out.println(obj.pri);
		System.out.println(obj.nomod);
		System.out.println(obj.pro);
		System.out.println(obj.pub);	
	}
}