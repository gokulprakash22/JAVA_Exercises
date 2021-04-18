package com.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CustomerMasterDAO;
import com.dao.UserDAO;
import com.model.CustomerMaster;
import com.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private CustomerMasterDAO customerMasterDAO;
	@Autowired
	private UserDAO userDAO;
	
	public final CustomerMasterDAO getCustomerMasterDAO() {
		return customerMasterDAO;
	}
	public final void setCustomerMasterDAO(CustomerMasterDAO customerMasterDAO) {
		this.customerMasterDAO = customerMasterDAO;
	}
	public final UserDAO getUserDAO() {
		return userDAO;
	}
	public final void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
@Override
public void createUser(CustomerMaster customerMaster) {
	customerMasterDAO.createCustomer(customerMaster);
}
@Override
public CustomerMaster authenticateUser(User user) {
	User actualUser = userDAO.getUserByEmail(user.getEmail());
	if(actualUser!=null) {
		if(actualUser.getPass().equals(user.getPass())) {
			CustomerMaster customerMaster = customerMasterDAO.getCustomerMaster(actualUser.getUid());
			return customerMaster;
		}
	}
	return null;
	
}
}
