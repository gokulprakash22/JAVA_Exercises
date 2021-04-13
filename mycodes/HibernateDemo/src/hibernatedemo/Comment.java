package hibernatedemo;

import java.io.Serializable;

public class Comment implements Serializable{
	private String msg;
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	public Comment(String msg) {
		this.msg=msg;
	}
	

	public final String getMsg() {
		return msg;
	}

	public final void setMsg(String msg) {
		this.msg = msg;
	}
	
}
