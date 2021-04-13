package inheritancedemo;

public class LakhaniShoeFactory extends ShoeFactory{
	private String lakhaniName;

	public final String getLakhaniName() {
		return lakhaniName;
	}

	public final void setLakhaniName(String lakhaniName) {
		this.lakhaniName = lakhaniName;
	}
	
	public void visit() {
		new Handler().handle(this);
	}
}
