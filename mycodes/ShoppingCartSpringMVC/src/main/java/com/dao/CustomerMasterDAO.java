package com.dao;

import com.model.CustomerMaster;

public interface CustomerMasterDAO {
	public void createCustomer(CustomerMaster customerMaster);
	public CustomerMaster getCustomerMaster(int customerid);
}
