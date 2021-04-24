package com.model;

import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class BundleData extends TagSupport{
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	@Override
	public int doEndTag() throws JspException {
		HttpSession session=pageContext.getSession();
		ResourceBundle rb=(ResourceBundle)session.getAttribute("rb");
		String value=rb.getString(key);
		JspWriter out=pageContext.getOut();
		try {
			out.println(value);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}
}
