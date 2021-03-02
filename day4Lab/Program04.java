package day4Lab;

public class Program04 {
	public static void main(String[] args) {
		try {
			throw new CustomException("message");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}

class CustomException extends Exception{
	String msg;
	public CustomException(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return msg;
	}
}
