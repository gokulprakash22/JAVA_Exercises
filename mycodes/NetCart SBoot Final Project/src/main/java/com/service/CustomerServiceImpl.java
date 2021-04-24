package com.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.CustomerMasterDAO;
import com.model.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerMasterDAO customerDao;
	
	public final CustomerMasterDAO getCustomerDao() {
		return customerDao;
	}
	public final void setCustomerDao(CustomerMasterDAO customerDao) {
		this.customerDao = customerDao;
	}
	@Override
	public void registerUser(Customer customer) {
		customerDao.registerUser(customer);
	}
	@Override
	public Customer loadCustomerByCustno(int custno) {
		return customerDao.getCustomerMaster(custno);
	}
	@Override
	public void updateCustomer(Customer customer) {
		customerDao.updateCustomer(customer);
		
	}
	
//	@Override
//	public int getCustno(String uname, String upass) {
//		return customerDao.getcustNo(uname,upass);
//	}
	@Override
	public Customer getCustomerDetails(String uname, String upass) {
		return customerDao.getCustomerMaster(uname, upass);
	}
}
