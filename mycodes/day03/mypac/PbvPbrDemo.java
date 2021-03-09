package mypac;

public class PbvPbrDemo {
	public static void main(String[] args) {
		Laddu ladduobj = new Laddu();
		ladduobj.setSize(10);
		System.out.println("Original size: "+ladduobj.getSize());
		
		//Pass by value
		PBV pbv = new PBV();
		pbv.met(ladduobj.getSize());
		System.out.println("Size after PBV: "+ladduobj.getSize());
		
		//Pass by reference
		PBR pbr = new PBR();
		pbr.met(ladduobj);
		System.out.println("Size after PBR: "+ladduobj.getSize());
	}
}

class PBV{
	public void met(int size) {
		size = size-5;
	}
}

class PBR{
	public void met(Laddu ladduobj) {
		ladduobj.setSize(ladduobj.getSize()-5);
	}
}

class Laddu{
	private int size;
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
}