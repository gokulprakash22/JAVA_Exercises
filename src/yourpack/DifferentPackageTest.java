package yourpack;

import mypac.ScopeDemo;

class SubTest extends ScopeDemo{
	//Different package subclass
	public void met() {
		//System.out.println(pri);
		//System.out.println(nomod);
		System.out.println(pro);
		System.out.println(pub);	
	}
}

class NonSubTest{
	//Different package non-subclass
	public void met() {
		ScopeDemo obj = new ScopeDemo();
		//System.out.println(obj.pri);
		//System.out.println(obj.nomod);
		//System.out.println(obj.pro);
		System.out.println(obj.pub);	
	}
}