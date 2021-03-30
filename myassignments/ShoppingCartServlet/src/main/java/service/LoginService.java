package service;

import model.invoice_db.CustomerMasterDTO;

public interface LoginService {
	public int authenticateUser(String email,String pass);
	public boolean checkUserNotLogedIn(int custno);
	public int updateCustomerFlag(int custno,int flag);
	public int registerNewCustomer(CustomerMasterDTO customerMasterDTO);
	public String getCustomerName(int custno);
}