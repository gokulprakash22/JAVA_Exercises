package com.dao;


import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.CustomerMaster;
import com.model.User;

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
public void createUser(User user) {
//	Session session=factory.openSession();
//	session.save(user);
//	session.beginTransaction().commit();
	
	Session session=factory.getCurrentSession();
	session.persist(user);
}
@Override
public User getUserByEmail(String email) {
	Session session=factory.getCurrentSession();
	Query query=session.createQuery("from User where email = :email");
	query.setString("email", email);
	User user= (User) query.uniqueResult();
	return user;
}
}
