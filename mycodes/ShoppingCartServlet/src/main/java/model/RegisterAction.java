package model;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.invoice_db.CustomerMasterDTO;
import service.LoginService;
import service.LoginServiceImpl;

public class RegisterAction extends Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String email=request.getParameter("email");
		String pass=request.getParameter("pass");
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		Properties prop=(Properties)request.getServletContext().getAttribute("properties");
		HttpSession session=request.getSession();
		
		LoginService loginService=LoginServiceImpl.getLoginServiceImpl(prop);
		CustomerMasterDTO customerMasterDTO=new CustomerMasterDTO();
		customerMasterDTO.setCustomeremail(email);
		customerMasterDTO.setCustomerpassword(pass);
		customerMasterDTO.setCustomername(name);
		customerMasterDTO.setCustomeraddress(address);
		customerMasterDTO.setCustomerphone(phone);

			if(loginService.registerNewCustomer(customerMasterDTO)>0) {

				return "register.success";
			}
		else {
			return "register.failure";
		}
	}
}
