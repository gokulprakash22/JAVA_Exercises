package com.controller;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model.CustomerMaster;
import com.model.User;
import com.service.UserService;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView loadIndex(ModelAndView mandv) {
		mandv.setViewName("index");
		return mandv;
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public ModelAndView loginForm(ModelAndView mandv, @RequestParam String language, HttpSession session) {
		ResourceBundle rb=ResourceBundle.getBundle("com.model.Dictionary",new Locale(language));
		session.setAttribute("rb", rb);
		User user=new User();
		mandv.addObject("user",user);
		mandv.addObject("loginMsg","Please Enter The Details");
		mandv.addObject("loginMsgColor","green");
		mandv.setViewName("login");
		return mandv;
	}
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public ModelAndView loginForm(ModelAndView mandv) {
		User user=new User();
		mandv.addObject("user",user);
		mandv.addObject("loginMsg","Please Enter The Details");
		mandv.addObject("loginMsgColor","green");
		mandv.setViewName("login");
		return mandv;
	}
	
	@RequestMapping(value="submitlogin", method=RequestMethod.POST)
	public ModelAndView submitLoginForm(User user, ModelAndView mandv, HttpSession session) {
		CustomerMaster customerMaster = userService.authenticateUser(user);
		System.out.println(customerMaster);
		if(customerMaster!=null) {
			session.setAttribute("customerMaster", customerMaster);
			mandv.addObject("customerMaster",customerMaster);
			mandv.setViewName("welcome");
		}
		else {
			User newUser=new User();
			mandv.addObject("user",newUser);
			mandv.addObject("loginMsg","Invalid Credentials");
			mandv.addObject("loginMsgColor","red");
			mandv.setViewName("login");
		}
		return mandv;
	}
	
	@RequestMapping(value="register", method=RequestMethod.GET)
	public ModelAndView registerForm(ModelAndView mandv) {
		CustomerMaster customerMaster =new CustomerMaster();
		mandv.addObject("customerMaster",customerMaster);
		mandv.setViewName("register");
		return mandv;
	}
	
	@RequestMapping(value="submitregister", method=RequestMethod.POST)
	public ModelAndView submitRegisterForm(CustomerMaster customerMaster, ModelAndView mandv, HttpSession session) {
		userService.createUser(customerMaster);
		session.setAttribute("customerMaster", customerMaster);
		mandv.addObject("customerMaster",customerMaster);
		mandv.setViewName("welcome");
		return mandv;
	}
	
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public ModelAndView submitRegisterForm(ModelAndView mandv) {
		mandv.setViewName("index");
		return mandv;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
