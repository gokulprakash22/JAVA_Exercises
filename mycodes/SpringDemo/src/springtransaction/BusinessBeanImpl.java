package springtransaction;

public class BusinessBeanImpl implements BusinessBean {
	
	private MoneyTransferBean mtb;
	

	public MoneyTransferBean getMtb() {
		return mtb;
	}

	public void setMtb(MoneyTransferBean mtb) {
		this.mtb = mtb;
	}
	
	@Override
	public void doTransaction(int credit_accid, int debit_accid, int amount) throws Exception {
		mtb.credit(credit_accid, amount);
		mtb.debit(debit_accid, amount);
	}
}
