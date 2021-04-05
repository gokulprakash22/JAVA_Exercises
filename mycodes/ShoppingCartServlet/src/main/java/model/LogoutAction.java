package model;

import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LoginService;
import service.LoginServiceImpl;

public class LogoutAction extends Action{
@Override
public String execute(HttpServletRequest request, HttpServletResponse response) {
	// TODO Auto-generated method stub
	HttpSession session=request.getSession();
	int custno=(int)session.getAttribute("custno");
	Properties prop=(Properties)request.getServletContext().getAttribute("properties");

	LoginService loginService=LoginServiceImpl.getLoginServiceImpl(prop);
	
	loginService.updateCustomerFlag(custno, 0);
	Enumeration<String> e=session.getAttributeNames();
	while(e.hasMoreElements()){
		String name=e.nextElement();
		session.removeAttribute(name);
	}
	return "logout.success";
}
}