package day1;

protected class Base{
	String method() {
		return "wow";
	}
}

class Derived{  
	public void useD() {
		Base zBase=new Base();
		System.out.println("base says "+zBase.method());
		
	}
}
/*
 *This will cause an error.
 *Class should be public or with no modifier.
 *Class should not be private or protected.
 *Inner classes can be private or protected.
 */