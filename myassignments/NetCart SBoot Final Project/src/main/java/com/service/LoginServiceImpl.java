package com.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CustomerMasterDAO;
import com.dao.UserDAO;
import com.model.Users;



@Service
@Transactional
public class LoginServiceImpl implements LoginService{
	@Autowired
	private UserDAO userDao;
	@Autowired
	private CustomerMasterDAO customerDao;
	
	public final UserDAO getUserDao() {
		return userDao;
	}
	public final void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
@Override
public void createUser(Users user) {
	userDao.insertUser(user);
}

@Override
public boolean checkUser(String uname, String upass,String role) {
	Users userDTO =userDao.getUserByName(uname);
	if(userDTO!=null) {
		if(userDTO.getUpass().equalsIgnoreCase(upass) && userDTO.getRole().equalsIgnoreCase(role)) {
			return true;
		}
		return false;
	}
	return false;
}

@Override
public int updateFlag(int uid, int flag) {
	return userDao.updateFlag(uid, flag);
}

@Override
public Users getUserByUName(String uname) {
	return userDao.getUserByName(uname);
}

@Override
public Users getUserByUID(int uid) {
	return userDao.getUserByUID(uid);
}
}
