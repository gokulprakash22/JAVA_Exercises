package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.User;
import com.service.UserService;

@Controller
@RequestMapping("login")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="loadform", method=RequestMethod.GET)
	public ModelAndView loadLoginForm(ModelAndView mandv) {
		User user=new User();
		mandv.addObject("user",user);
		mandv.setViewName("login");
		return mandv;
	}
	
	@RequestMapping(value="submitform", method=RequestMethod.POST)
	public ModelAndView submitLoginForm(User user,ModelAndView mandv) {
		userService.createUser(user);
		mandv.addObject("user",user);
		mandv.setViewName("welcome");
		return mandv;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
