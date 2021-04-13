package inheritancedemo;

public class BataShoeFactory extends ShoeFactory{
	private String bataName;

	public final String getBataName() {
		return bataName;
	}

	public final void setBataName(String bataName) {
		this.bataName = bataName;
	}
	
	public void visit() {
		new Handler().handle(this);
	}
}
