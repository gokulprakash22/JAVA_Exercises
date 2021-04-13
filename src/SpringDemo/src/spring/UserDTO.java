package spring;

import java.io.Serializable;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String pass;
	private int flag;
	
	public UserDTO(int id, String name, String pass, int flag) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.flag = flag;
	}
	
	public static UserDTO userDTO;
	
	public static UserDTO getUserDTO() {
		if(userDTO == null) {
			return new UserDTO();
		} else {
			return userDTO.createClone();
		}
	}
	
	public UserDTO createClone() {
		try {
			return (UserDTO)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + flag;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
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
		UserDTO other = (UserDTO) obj;
		if (flag != other.flag)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", pass=" + pass + ", flag=" + flag + "]";
	}	
}
