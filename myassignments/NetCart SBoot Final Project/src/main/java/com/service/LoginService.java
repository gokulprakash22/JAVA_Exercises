package com.service;

import com.model.Users;

public interface LoginService {
	public void createUser(Users user);
	public boolean checkUser(String uname, String upass,String role);
	public int updateFlag(int uid,int flag);
	public Users getUserByUName(String uname);
	public Users getUserByUID(int uid);
}
