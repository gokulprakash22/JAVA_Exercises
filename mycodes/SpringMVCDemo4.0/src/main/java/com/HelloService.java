package com;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("hello")
public class HelloService {
	@Autowired
	private SessionFactory factory;
	
	
	public final SessionFactory getFactory() {
		return factory;
	}
	public final void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	@RequestMapping(value="sayhello", method=RequestMethod.GET)
	public void sayHello() {
		System.out.println("say hello called................"+factory);
	}
}
