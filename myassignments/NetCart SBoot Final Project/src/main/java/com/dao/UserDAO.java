package com.dao;



import com.model.Users;

public interface UserDAO {
	public int deleteUser(int uid);
	public int updateFlag(int uid,int flag);
	public int updateUser(Users userDTO);
	public int insertUser(Users userDTO);
	public Users getUserByName(String uname);
	public Users getUserByUID(int uid);
}
