package com.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDAO;
import com.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDAO userDao;
	
	public final UserDAO getUserDao() {
		return userDao;
	}
	public final void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
@Override
public void createUser(User user) {
	userDao.createUser(user);
}
}
