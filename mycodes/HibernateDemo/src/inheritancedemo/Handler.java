package inheritancedemo;

public class Handler {
	public void handle(ShoeFactory factory) {
		System.out.println(factory.getName());
	}

	public void handle(BataShoeFactory bata) {
		System.out.println(bata.getBataName());
	}

	public void handle(LakhaniShoeFactory lakhani) {
		System.out.println(lakhani.getLakhaniName());
	}
}
