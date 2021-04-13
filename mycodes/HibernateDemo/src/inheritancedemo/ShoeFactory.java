package inheritancedemo;

public class ShoeFactory {
	private int sid;
	private String name;
	
	public final int getSid() {
		return sid;
	}
	public final void setSid(int sid) {
		this.sid = sid;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ShoeFactory [sid=" + sid + ", name=" + name + "]";
	}
	
	public void visit() {
		new Handler().handle(this);
	}
}
