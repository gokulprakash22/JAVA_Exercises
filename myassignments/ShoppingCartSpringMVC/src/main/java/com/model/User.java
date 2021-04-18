package com.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Cloneable, Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int uid;
	
	private String email,pass;
	private int flag;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(int uid, String email, String pass, int flag) {
		super();
		this.uid = uid;
		this.email = email;
		this.pass = pass;
		this.flag = flag;
	}
	
	public User getClone() {
		try {
			return (User) super.clone();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public final int getUid() {
		return uid;
	}

	public final void setUid(int uid) {
		this.uid = uid;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final String getPass() {
		return pass;
	}

	public final void setPass(String pass) {
		this.pass = pass;
	}

	public final int getFlag() {
		return flag;
	}

	public final void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", email=" + email + ", pass=" + pass + ", flag=" + flag + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + flag;
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + uid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (flag != other.flag)
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		if (uid != other.uid)
			return false;
		return true;
	}

}
