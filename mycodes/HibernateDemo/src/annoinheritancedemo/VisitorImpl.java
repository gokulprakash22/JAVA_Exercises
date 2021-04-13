package annoinheritancedemo;

public class VisitorImpl implements Visitor{
	public void process(Automobile bsf) {
		System.out.println("auto processed...");
	}
	public void process(Car lsf) {
		System.out.println("car processed...");
	}
	public void process(Maruti sf) {
		System.out.println("maruti processed");
	}
}
