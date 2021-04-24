package com.dao;


import com.model.Customer;

public interface CustomerMasterDAO {
	public int registerUser(Customer customer);
	public int updateCustomer(Customer customerMasterDTO);
	public Customer getCustomerMaster(int custno);
	public Customer getCustomerMaster(String uname,String upass);
//	public int getcustNo(String uname,String pass);

}
