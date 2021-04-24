package com.controller;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.model.Customer;
import com.model.Users;
import com.service.CustomerService;
import com.service.LoginService;
import com.service.ShoppingService;

@Controller
@RequestMapping("")
public class LoginController {
	@Autowired
	private ShoppingService shoppingService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="netcart", method=RequestMethod.GET)
	public ModelAndView loadIndexForm(ModelAndView mandv) {
		mandv.setViewName("index");
		return mandv;
	}
	
	@RequestMapping(value="loadForm", method=RequestMethod.POST)
	public ModelAndView loadLoginForm(HttpServletRequest request,ModelAndView mandv) {
		String language=request.getParameter("language");
		ResourceBundle rb=ResourceBundle.getBundle("com.model.Dictionary",new Locale(language));
		HttpSession session=request.getSession();
		session.setAttribute("rb", rb);
		Users user=new Users();
		mandv.addObject("user",user);
		mandv.setViewName("login");
		return mandv;
	}
	
	@RequestMapping(value="loadRegister", method=RequestMethod.GET)
	public ModelAndView loadRegisterForm(ModelAndView mandv) {
		Customer customer=new Customer();
		mandv.addObject("customerdetails",customer);
		mandv.setViewName("register");
		return mandv;
	}
	
	
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request,Customer customerdetails,ModelAndView mandv) {
		String confirmpassword=request.getParameter("confirmpassword");
		System.out.println(confirmpassword);
		if(customerdetails.getUser().getUpass().equals(confirmpassword)) {
	    customerService.registerUser(customerdetails);
		mandv.setViewName("index");
	    }
		else {
			Customer customer=new Customer();
			mandv.addObject("customerdetails",customer);
			mandv.setViewName("register");
		}
		return mandv;
		
	}
	
	@RequestMapping(value="validateForm", method=RequestMethod.POST)
	public ModelAndView validateLoginForm(HttpServletRequest request,Users user,ModelAndView mandv) {
		
		if(loginService.checkUser(user.getUname(), user.getUpass(),user.getRole())) {
			
			loginService.updateFlag(user.getUid(),1);
			HttpSession session=request.getSession();
			session.setMaxInactiveInterval(300);
			
			//for session listener
			session.setAttribute("uid",user.getUid());
			
			
			if(user.getRole().equals("customer")) {
				Customer customer=customerService.getCustomerDetails(user.getUname(), user.getUpass());
				session.setAttribute("customer",customer);
				session.setAttribute("custno", customer.getCustomerno());
				mandv.setViewName("redirect:/shop/userhome");
			}
			else if(user.getRole().equals("store-admin")){
				session.setAttribute("admindetails",customerService.getCustomerDetails(user.getUname(), user.getUpass()));
				mandv.setViewName("redirect:/store/displayitems");
				}
			else if(user.getRole().equals("management")){
				session.setAttribute("managementdetails",customerService.getCustomerDetails(user.getUname(), user.getUpass()));
				mandv.setViewName("redirect:/manage/managementhome");}
		}
		
		else {
			mandv.addObject("user",user);
			mandv.setViewName("login");
		}
		return mandv;
	}
	
	@RequestMapping(value="loadWelcome", method=RequestMethod.GET)
	public ModelAndView loadWelcomeForm(HttpServletRequest request,Users user,ModelAndView mandv) {
		mandv.addObject("user",user);
		mandv.setViewName("welcome");
		return mandv;
	}
	@RequestMapping(value="loadLanding", method=RequestMethod.GET)
	public ModelAndView loadLanding(HttpServletRequest request,ModelAndView mandv) {
		HttpSession session=request.getSession();
		Users user=loginService.getUserByUID((int)session.getAttribute("custno"));
		mandv.addObject("user",user);
		mandv.setViewName("welcome");
		return mandv;
	}
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request,Users user,ModelAndView mandv) {
		HttpSession session=request.getSession();
		session.invalidate();
		shoppingService.setInvoicewrapper(null);
		mandv.setViewName("redirect:netcart");
		return mandv;
	}
	
	
	@RequestMapping(value="accesserror", method=RequestMethod.GET)
	public ModelAndView accessdenied(ModelAndView mandv) {
		mandv.setViewName("accessdenied");
		return mandv;
	}
	
	public LoginService getUserService() {
		return loginService;
	}

	public void setUserService(LoginService userService) {
		this.loginService = userService;
	}
}
