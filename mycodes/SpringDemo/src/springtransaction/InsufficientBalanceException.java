package springtransaction;

public class InsufficientBalanceException extends Exception {
	String msg;
	
	public InsufficientBalanceException(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "InsufficientBalanceException [msg=" + msg + "]";
	}
}
