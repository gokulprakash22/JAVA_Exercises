package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LoginService;
import service.LoginServiceImpl;

public class LoginAction extends Action{
@Override
public String execute(HttpServletRequest request, HttpServletResponse response) {
	String email=request.getParameter("email");
	String pass=request.getParameter("pass");
	Properties prop=(Properties)request.getServletContext().getAttribute("properties");
	HttpSession session=request.getSession();
	LoginService loginService=LoginServiceImpl.getLoginServiceImpl(prop);
	int custno = loginService.authenticateUser(email, pass);
	if(custno!=-1) {
		if(loginService.checkUserNotLogedIn(custno)) {
			session.setAttribute("custno", custno);
			session.setAttribute("email", email);
			session.setAttribute("name", loginService.getCustomerName(custno));
			loginService.updateCustomerFlag(custno, 1);
			return "login.success";
		}
		else {
			return "login.already";
		}
	}
	else {
		return "login.failure";
	}
}
}