package com.service;

import com.model.Customer;

public interface CustomerService {
	public void registerUser(Customer customer);
	public Customer loadCustomerByCustno(int custno);
	public void updateCustomer(Customer customer);
//	public int getCustno(String uname,String pass);
	public Customer getCustomerDetails(String uname,String upass);
}
