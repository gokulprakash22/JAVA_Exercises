package day1;

public class Program22_AccessModifiers {
	public static void main(String[] args) {
		AccessSpecification accessobj=new AccessSpecification();
		System.out.println("Public Variable Value: "+accessobj.publicvar);
		//System.out.println("Private Variable Value: "+accessobj.privatevar); // Private variable not accessible
		System.out.println("Protected Variable Value: "+accessobj.protectedvar);
		System.out.println("Default Variable Value: "+accessobj.nomodifiervar);
		accessobj.publicMet();
	    //accessobj.privateMet();  //Private method not accessible
	    accessobj.protectedMet();
	    accessobj.noModifierMet();
	}
}
class AccessSpecification{
	//Variables
	public int publicvar=1;
	private int privatevar=2;
	protected int protectedvar=3;
	int nomodifiervar=4;
	
	//Methods
	public void publicMet() {
		System.out.println("Public Method");
	}
	private void privateMet() {
		System.out.println("Private Method");
	}
	protected void protectedMet() {
		System.out.println("Protected Method");
	}
	void noModifierMet() {
		System.out.println("No Modifier Method");
	}
}