package com.service;

import com.model.CustomerMaster;
import com.model.User;

public interface UserService {
	public void createUser(CustomerMaster customerMaster);
	public CustomerMaster authenticateUser(User user);
}
