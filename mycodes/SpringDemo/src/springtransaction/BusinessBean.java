package springtransaction;

public interface BusinessBean {
	public void doTransaction(int credit_accid, int debit_accid, int amount) throws Exception;
}
