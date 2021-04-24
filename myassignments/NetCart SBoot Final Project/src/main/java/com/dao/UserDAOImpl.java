package com.dao;


import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Users;


@Repository
public class UserDAOImpl implements UserDAO{
	@Autowired
	private SessionFactory factory;
	
	public final SessionFactory getFactory() {
		return factory;
	}
	public final void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	
	@Override
	public int deleteUser(int uid) {
		Session session=factory.getCurrentSession();
	    Query query = session.createQuery("DELETE from Users where uid = :uid");
	    query.setParameter("uid", uid);
	     int row = query.executeUpdate();
		return row;
	}
	@Override
	public int updateUser(Users userDTO) {
		Session session=factory.getCurrentSession();
		System.out.println(userDTO);
	    Query query = session.createQuery("update Users set uname=:uname,upass=:upass,flag=:flag where uid=:uid");
	    query.setParameter("uname",userDTO.getUname());  
	    query.setParameter("upass",userDTO.getUpass());
	    query.setParameter("flag",userDTO.getFlag());
	    query.setParameter("uid",userDTO.getUid());
	    int row = query.executeUpdate();
		return row;
	}
	@Override
	public int insertUser(Users userDTO) {
		Session session=factory.getCurrentSession();
		session.persist(userDTO);
		return 0;
	}
	@Override
	public Users getUserByName(String uname) {
		Session session=factory.getCurrentSession();
		Query query=session.createQuery("from Users where uname = :uname");
		query.setParameter("uname", uname);
		Users user = (Users) query.uniqueResult();
		return user;
	}
	@Override
	public Users getUserByUID(int uid) {
		Session session=factory.getCurrentSession();
		Query query=session.createQuery("from Users where uid = :uid");
		query.setParameter("uid", uid);
		Users user = (Users) query.uniqueResult();
		return user;
	}
	@Override
	public int updateFlag(int uid, int flag) {
		try {
			Session session=factory.getCurrentSession();
			Query query=session.createQuery("Update Users set flag=:f where uid=:u");
			query.setParameter("f", flag);
			query.setParameter("u", uid);
			return query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
	}


}
